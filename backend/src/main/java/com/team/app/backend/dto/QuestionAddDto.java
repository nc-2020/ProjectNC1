package com.team.app.backend.dto;

import com.team.app.backend.persistance.model.QuestionType;

public class QuestionAddDto {
    private Integer time;
    private String text;
    private Integer max_points;
    private byte[] image;
    private QuestionType type;
    private Long quiz_id;

    public Integer getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getMax_points() {
        return max_points;
    }

    public void setMax_points(Integer max_points) {
        this.max_points = max_points;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Long quiz_id) {
        this.quiz_id = quiz_id;
    }
}
