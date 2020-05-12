package com.team.app.backend.persistance.model;

import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.Date;

public class Session {

    private Long id;
    private Long quizId;
    private String accessCode;
    private Date date;
    private SessionStatus status;

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public Session() {}

    public Session(Long quiz_id) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        this.quizId = quiz_id;
        this.date = date;
        this.accessCode = "";
        this.status=new SessionStatus(1L,"waiting");
    }

    public Long getId() {
        return id;
    }

    public Session setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getQuiz_id() {
        return quizId;
    }

    public Session setQuiz_id(Long quiz_id) {
        this.quizId = quiz_id;
        return this;
    }

    public String getAccess_code() {
        return accessCode;
    }

    public Session setAccess_code(String access_code) {
        this.accessCode = access_code;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Session setDate(Date date) {
        this.date = date;
        return this;
    }
}
