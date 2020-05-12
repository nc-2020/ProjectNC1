package com.team.app.backend.persistance.model;

import java.util.Date;

public class Notification {
    private Long id;
    private String text;
    private boolean seen;
    private Date date;
    private Long categoryId;
    private Long userId;

    public Notification(Long id, String text, boolean seen, Date date, Long categoryId, Long userId) {
        this.id = id;
        this.text = text;
        this.seen = seen;
        this.date = date;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Notification() {
        this.seen = false;
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

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCategoryID() {
        return categoryId;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryId = categoryID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
