package com.prepai.model;

import java.util.List;

public class Answer {
    private String questionId;
    private String questionText;
    private String userAnswer;
    private List<String> keywords;
    private String sampleAnswer;

    // Constructors
    public Answer() {}

    public Answer(String questionId, String questionText, String userAnswer, List<String> keywords, String sampleAnswer) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.userAnswer = userAnswer;
        this.keywords = keywords;
        this.sampleAnswer = sampleAnswer;
    }

    // Getters and Setters
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getSampleAnswer() {
        return sampleAnswer;
    }

    public void setSampleAnswer(String sampleAnswer) {
        this.sampleAnswer = sampleAnswer;
    }
}
