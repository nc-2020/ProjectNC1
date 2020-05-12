package com.team.app.backend.dto;

public class FinishedQuizDto {
    long user_id;
    long ses_id;
    int score;

    public FinishedQuizDto() {}

    public FinishedQuizDto(long user_id, long ses_id, int score) {
        this.user_id = user_id;
        this.ses_id = ses_id;
        this.score = score;
    }

    public long getUser_id() {
        return user_id;
    }

    public FinishedQuizDto setUser_id(long user_id) {
        this.user_id = user_id;
        return this;
    }

    public long getSes_id() {
        return ses_id;
    }

    public FinishedQuizDto setSes_id(long ses_id) {
        this.ses_id = ses_id;
        return this;
    }

    public int getScore() {
        return score;
    }

    public FinishedQuizDto setScore(int score) {
        this.score = score;
        return this;
    }
}
