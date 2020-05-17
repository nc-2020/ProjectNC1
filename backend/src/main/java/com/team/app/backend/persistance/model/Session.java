package com.team.app.backend.persistance.model;

import org.jasypt.util.text.BasicTextEncryptor;

import java.sql.Date;
import java.sql.Timestamp;

public class Session {

    private Long id;
    private Long quiz_id;
    private String accessCode;
    private Timestamp date;
    private SessionStatus status;

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public Session() {}

    public Session(Long quiz_id, String accessCode, Timestamp date, SessionStatus status) {
        this.quiz_id = quiz_id;
        this.accessCode = accessCode;
        this.date = date;
        this.status = status;
    }



    public Long getId() {
        return id;
    }

    public Session setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getQuiz_id() {
        return quiz_id;
    }

    public Session setQuiz_id(Long quiz_id) {
        this.quiz_id = quiz_id;

        return this;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public Session setAccessCode(String access_code) {
        this.accessCode = access_code;
        return this;
    }

    public Timestamp getDate() {
        return date;
    }

    public Session setDate(Timestamp date) {
        this.date = date;
        return this;
    }
}
