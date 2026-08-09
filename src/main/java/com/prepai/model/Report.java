package com.prepai.model;

import java.util.List;

public class Report {
    private String id;
    private String role;
    private String difficulty;
    private String type;
    private String date;
    private int durationSeconds;
    private int overallScore;
    private int commScore;
    private int techScore;
    private int psScore;
    private List<QuestionReview> questions;

    // Constructors
    public Report() {}

    public Report(String id, String role, String difficulty, String type, String date, int durationSeconds,
                  int overallScore, int commScore, int techScore, int psScore, List<QuestionReview> questions) {
        this.id = id;
        this.role = role;
        this.difficulty = difficulty;
        this.type = type;
        this.date = date;
        this.durationSeconds = durationSeconds;
        this.overallScore = overallScore;
        this.commScore = commScore;
        this.techScore = techScore;
        this.psScore = psScore;
        this.questions = questions;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public int getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }

    public int getOverallScore() { return overallScore; }
    public void setOverallScore(int overallScore) { this.overallScore = overallScore; }

    public int getCommScore() { return commScore; }
    public void setCommScore(int commScore) { this.commScore = commScore; }

    public int getTechScore() { return techScore; }
    public void setTechScore(int techScore) { this.techScore = techScore; }

    public int getPsScore() { return psScore; }
    public void setPsScore(int psScore) { this.psScore = psScore; }

    public List<QuestionReview> getQuestions() { return questions; }
    public void setQuestions(List<QuestionReview> questions) { this.questions = questions; }

    // Nested Class for Question Reviews
    public static class QuestionReview {
        private String questionText;
        private String userAnswer;
        private int score;
        private String feedback;
        private List<String> matchedKeywords;
        private String sampleAnswer;

        public QuestionReview() {}

        public QuestionReview(String questionText, String userAnswer, int score, String feedback,
                              List<String> matchedKeywords, String sampleAnswer) {
            this.questionText = questionText;
            this.userAnswer = userAnswer;
            this.score = score;
            this.feedback = feedback;
            this.matchedKeywords = matchedKeywords;
            this.sampleAnswer = sampleAnswer;
        }

        // Getters and Setters
        public String getQuestionText() { return questionText; }
        public void setQuestionText(String questionText) { this.questionText = questionText; }

        public String getUserAnswer() { return userAnswer; }
        public void setUserAnswer(String userAnswer) { this.userAnswer = userAnswer; }

        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }

        public String getFeedback() { return feedback; }
        public void setFeedback(String feedback) { this.feedback = feedback; }

        public List<String> getMatchedKeywords() { return matchedKeywords; }
        public void setMatchedKeywords(List<String> matchedKeywords) { this.matchedKeywords = matchedKeywords; }

        public String getSampleAnswer() { return sampleAnswer; }
        public void setSampleAnswer(String sampleAnswer) { this.sampleAnswer = sampleAnswer; }
    }
}
