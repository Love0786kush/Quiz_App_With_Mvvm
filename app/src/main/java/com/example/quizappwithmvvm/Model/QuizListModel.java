package com.example.quizappwithmvvm.Model;

import com.google.firebase.firestore.DocumentId;

public class QuizListModel {
    @DocumentId
    private String quizId;
    private String title;
    private String image;
    private String difficulty;
    private long questionCount;

    /** No-arg constructor required for Firestore deserialization */
    public QuizListModel() { }

    /** Full-arg constructor for manual instantiation */
    public QuizListModel(String quizId, String title, String image, String difficulty, long questionCount) {
        this.quizId = quizId;
        this.title = title;
        this.image = image;
        this.difficulty = difficulty;
        this.questionCount = questionCount;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public long getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(long questionCount) {
        this.questionCount = questionCount;
    }
}
