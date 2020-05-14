package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
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
}
