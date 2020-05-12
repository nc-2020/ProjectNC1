package com.team.app.backend.persistance.model;

import java.util.Date;

public abstract class Message {
    private Long id;
    private String text;
    private Date date;
    private Long categoryId;
    private Long userId;

    public Message(Long id, String text, Date date, Long categoryId, Long userId) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Message() {
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
