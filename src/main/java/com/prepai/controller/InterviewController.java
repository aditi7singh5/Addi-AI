package com.prepai.controller;

import com.prepai.model.Answer;
import com.prepai.model.Question;
import com.prepai.model.Report;
import com.prepai.service.AssessmentService;
import com.prepai.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow frontend calls from other ports during dev
public class InterviewController {

    private final QuestionService questionService;
    private final AssessmentService assessmentService;

    @Autowired
    public InterviewController(QuestionService questionService, AssessmentService assessmentService) {
        this.questionService = questionService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(
            @RequestParam String role,
            @RequestParam String level,
            @RequestParam String type,
            @RequestParam(defaultValue = "3") int limit) {
        return questionService.getQuestions(role, level, type, limit);
    }

    @PostMapping("/assess")
    public Report assess(@RequestBody AssessmentRequest request) {
        return assessmentService.assessInterview(
                request.getRole(),
                request.getDifficulty(),
                request.getType(),
                request.getDurationSeconds(),
                request.getAnswers()
        );
    }

    @PostMapping("/interact")
    public String interact(@RequestBody InteractionRequest request) {
        return assessmentService.getTransition(
                request.getQuestion(),
                request.getUserAnswer(),
                request.getNextQuestion()
        );
    }

    @GetMapping("/history")
    public List<Report> getHistory() {
        return assessmentService.getHistory();
    }

    // Request DTO for parsing JSON post requests
    public static class AssessmentRequest {
        private String role;
        private String difficulty;
        private String type;
        private int durationSeconds;
        private List<Answer> answers;

        // Getters and Setters
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getDifficulty() { return difficulty; }
        public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public int getDurationSeconds() { return durationSeconds; }
        public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }

        public List<Answer> getAnswers() { return answers; }
        public void setAnswers(List<Answer> answers) { this.answers = answers; }
    }

    public static class InteractionRequest {
        private String question;
        private String userAnswer;
        private String nextQuestion;

        public String getQuestion() { return question; }
        public void setQuestion(String question) { this.question = question; }

        public String getUserAnswer() { return userAnswer; }
        public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }

        public String getNextQuestion() { return nextQuestion; }
        public void setNextQuestion(String nextQuestion) { this.nextQuestion = nextQuestion; }
    }
}
