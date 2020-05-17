package com.team.app.backend.persistance.model;

import java.sql.Timestamp;

public abstract class Message {
    private Long id;
    private String text;
    private Timestamp date;
    private Long categoryId;
    private Long userId;

    public Message(Long id, String text, Timestamp date, Long categoryId, Long userId) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Message() {
        long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new java.sql.Timestamp(millis);
        this.date = date;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
