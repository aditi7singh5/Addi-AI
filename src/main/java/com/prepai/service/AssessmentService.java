package com.prepai.service;

import com.prepai.model.Answer;
import com.prepai.model.Report;
import com.prepai.model.Report.QuestionReview;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class AssessmentService {
    private final List<Report> history = new ArrayList<>();
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    public List<Report> getHistory() {
        List<Report> reversed = new ArrayList<>(history);
        Collections.reverse(reversed);
        return reversed;
    }

    public Report assessInterview(String role, String difficulty, String type, int durationSeconds, List<Answer> answers) {
        List<QuestionReview> reviews = new ArrayList<>();
        
        int totalScore = 0;
        int communicationSum = 0;
        int technicalSum = 0;
        int problemSolvingSum = 0;

        boolean useAI = apiKey != null && !apiKey.trim().isEmpty();

        for (Answer ans : answers) {
            String text = ans.getUserAnswer();
            QuestionReview review = null;

            if (text == null || text.trim().isEmpty()) {
                review = new QuestionReview(
                    ans.getQuestionText(),
                    "Skipped",
                    0,
                    "No response was provided. A structured response mapping your capabilities is essential for standard grading.",
                    Collections.emptyList(),
                    ans.getSampleAnswer()
                );
            } else {
                text = text.trim();
                
                if (useAI) {
                    review = assessWithGemini(role, difficulty, type, ans, text);
                }
                
                // Fallback to local rule-based assessment if AI was not run or failed
                if (review == null) {
                    review = assessLocally(ans, text);
                }
            }

            communicationSum += getSubScore(review.getScore(), 1.05f);
            technicalSum += getSubScore(review.getScore(), 0.95f);
            problemSolvingSum += getSubScore(review.getScore(), 1.0f);
            totalScore += review.getScore();

            reviews.add(review);
        }

        int totalQuestions = Math.max(1, answers.size());
        int finalScore = Math.round((float) totalScore / totalQuestions);
        int finalComm = Math.round((float) communicationSum / totalQuestions);
        int finalTech = Math.round((float) technicalSum / totalQuestions);
        int finalPS = Math.round((float) problemSolvingSum / totalQuestions);

        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy, hh:mm a", Locale.US);
        String dateStr = sdf.format(new Date());

        Report report = new Report(
            "report_" + System.currentTimeMillis(),
            role,
            difficulty,
            type,
            dateStr,
            durationSeconds,
            finalScore,
            finalComm,
            finalTech,
            finalPS,
            reviews
        );

        history.add(report);
        return report;
    }

    private QuestionReview assessWithGemini(String role, String difficulty, String type, Answer ans, String text) {
        try {
            String systemInstructions = "You are an expert technical interviewer assessing a candidate's response to an interview question.\n\n" +
                    "ROLE: " + role + "\n" +
                    "DIFFICULTY LEVEL: " + difficulty + "\n" +
                    "QUESTION FOCUS TYPE: " + type + "\n" +
                    "QUESTION: " + ans.getQuestionText() + "\n" +
                    "HINT/FOCUS GUIDELINES: " + ans.getKeywords() + "\n" +
                    "EXPERT SAMPLE ANSWER REFERENCE: " + ans.getSampleAnswer() + "\n" +
                    "CANDIDATE'S ANSWER: " + text + "\n\n" +
                    "Grade the candidate's answer and return a JSON object with the following fields:\n" +
                    "- score (integer, 0 to 100): Overall rating of the answer.\n" +
                    "- feedback (string): Detailed qualitative feedback containing strengths and specific growth points.\n" +
                    "- matchedKeywords (array of strings): List of high-value industry terms or keywords they correctly used in their answer.\n" +
                    "- commScore (integer, 0 to 100): Communication skill grade.\n" +
                    "- techScore (integer, 0 to 100): Technical depth and accuracy grade.\n" +
                    "- psScore (integer, 0 to 100): Problem solving capability grade.\n\n" +
                    "You MUST return ONLY the JSON object. Do not include markdown formatting or HTML wraps.";

            // Construct Gemini REST payload
            Map<String, Object> requestBody = new HashMap<>();
            List<Map<String, Object>> contents = new ArrayList<>();
            Map<String, Object> contentMap = new HashMap<>();
            List<Map<String, Object>> parts = new ArrayList<>();
            Map<String, Object> partMap = new HashMap<>();
            partMap.put("text", systemInstructions);
            parts.add(partMap);
            contentMap.put("parts", parts);
            contents.add(contentMap);
            requestBody.put("contents", contents);

            Map<String, Object> genConfig = new HashMap<>();
            genConfig.put("responseMimeType", "application/json");
            requestBody.put("generationConfig", genConfig);

            // Execute REST POST call
            String url = apiUrl + "?key=" + apiKey;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode partNode = root.path("candidates").get(0).path("content").path("parts").get(0);
                String rawJson = partNode.path("text").asText();
                
                // Parse returned structured evaluation
                JsonNode result = objectMapper.readTree(rawJson);
                
                int score = result.path("score").asInt();
                String feedback = result.path("feedback").asText();
                List<String> matchedKeywords = new ArrayList<>();
                if (result.has("matchedKeywords")) {
                    result.path("matchedKeywords").forEach(k -> matchedKeywords.add(k.asText()));
                }

                return new QuestionReview(
                    ans.getQuestionText(),
                    text,
                    score,
                    feedback,
                    matchedKeywords,
                    ans.getSampleAnswer()
                );
            }
        } catch (Exception e) {
            System.err.println("Gemini grading failed, falling back to local analysis: " + e.getMessage());
        }
        return null;
    }

    private QuestionReview assessLocally(Answer ans, String text) {
        List<String> matchedKeywords = new ArrayList<>();
        String[] words = text.split("\\s+");
        int wordCount = words.length;

        if (ans.getKeywords() != null) {
            for (String kw : ans.getKeywords()) {
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(kw) + "\\b", Pattern.CASE_INSENSITIVE);
                if (pattern.matcher(text).find()) {
                    matchedKeywords.add(kw);
                }
            }
        }

        int lengthScore = Math.min(45, Math.round((wordCount / 100f) * 45));
        int kwScore = 0;
        if (ans.getKeywords() != null && !ans.getKeywords().isEmpty()) {
            float ratio = (float) matchedKeywords.size() / ans.getKeywords().size();
            kwScore = Math.round(ratio * 45);
        } else {
            kwScore = 45;
        }

        int structureBonus = text.length() > 250 ? 10 : 5;
        int score = Math.min(100, lengthScore + kwScore + structureBonus);
        String feedback = "";

        if (score < 50) {
            feedback = "The answer is relatively brief. Try expanding your response. Consider referencing structural components like: " +
                    (ans.getKeywords() != null && ans.getKeywords().size() >= 3 
                            ? String.join(", ", ans.getKeywords().subList(0, 3)) 
                            : "specific technical terms") + ".";
        } else if (score < 80) {
            feedback = "Solid structure and details (Local Analysis Fallback). You correctly mentioned key principles like: \"" +
                    String.join(", ", matchedKeywords) + "\". To get an elite score, try diving deeper into direct trade-offs.";
        } else {
            feedback = "Excellent answer (Local Analysis Fallback)! Demonstrates clear technical competence. Comprehensive coverage of terms: \"" +
                    String.join(", ", matchedKeywords) + "\".";
        }

        return new QuestionReview(
            ans.getQuestionText(),
            text,
            score,
            feedback,
            matchedKeywords,
            ans.getSampleAnswer()
        );
    }

    private int getSubScore(int baseScore, float scale) {
        return Math.min(100, Math.round(baseScore * scale));
    }
}
