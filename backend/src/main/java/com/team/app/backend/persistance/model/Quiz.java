package com.team.app.backend.persistance.model;

import java.util.Date;

public class Quiz {
    private Long id;
    private String title;
    private Date date;
    private String discription;
    private byte[] image;
    private QuizStatus status;
    private Long user_id;

    public Quiz() {
    }

    public Quiz(Long id, String title, Date date, String discription, byte[] image, QuizStatus status, Long user_id) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.discription = discription;
        this.image = image;
        this.status = status;
        this.user_id = user_id;
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

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public QuizStatus getStatus() {
        return status;
    }

    public void setStatus_Id(QuizStatus status) {
        this.status = status;
    }

    public Long getUser() {
        return user_id;
    }

    public void setUser(Long user) {
        this.user_id = user_id;
    }
}
