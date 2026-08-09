package com.prepai.model;

import java.util.List;

public class Question {
    private String id;
    private String question;
    private String hint;
    private String sampleAnswer;
    private List<String> keywords;

    // Constructors
    public Question() {}

    public Question(String id, String question, String hint, String sampleAnswer, List<String> keywords) {
        this.id = id;
        this.question = question;
        this.hint = hint;
        this.sampleAnswer = sampleAnswer;
        this.keywords = keywords;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSampleAnswer() {
        return sampleAnswer;
    }

    public void setSampleAnswer(String sampleAnswer) {
        this.sampleAnswer = sampleAnswer;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
