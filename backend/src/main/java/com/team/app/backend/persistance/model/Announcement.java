package com.team.app.backend.persistance.model;

import java.util.Date;

public class Announcement {
    private Long id;
    private String title;
    private String text;
    private Date date;
    private byte[] image;
    private Long statusId;
    private Long userId;
    private Long categoryId;

    public Announcement(){

        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        this.date = date;
    }

    public Announcement(Long id, String title, String text, Date date, byte[] image, Long statusId,Long categoryId, Long userId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.image = image;
        this.statusId = statusId;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
