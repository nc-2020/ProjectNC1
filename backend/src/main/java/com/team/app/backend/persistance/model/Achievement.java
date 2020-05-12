package com.team.app.backend.persistance.model;

public class Achievement {
    private Long id;
    private String title;
    private Long amountOfQuizzes;
    private Long amountOfCreated;
    private byte[] icon;
    private Long creatorUserId;
    private Long categoryId;

    public Achievement() {
    }

    public Achievement(Long id, String title, Long amountOfQuizzes, Long amountOfCreated, byte[] icon, Long creatorUserId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.amountOfQuizzes = amountOfQuizzes;
        this.amountOfCreated = amountOfCreated;
        this.icon = icon;
        this.creatorUserId = creatorUserId;
        this.categoryId = categoryId;
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

    public Long getAmountOfQuizzes() {
        return amountOfQuizzes;
    }

    public void setAmountOfQuizzes(Long amountOfQuizzes) {
        this.amountOfQuizzes = amountOfQuizzes;
    }

    public Long getAmountOfCreated() {
        return amountOfCreated;
    }

    public void setAmountOfCreated(Long amountOfCreated) {
        this.amountOfCreated = amountOfCreated;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
