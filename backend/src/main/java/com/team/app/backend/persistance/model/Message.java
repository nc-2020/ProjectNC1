package com.team.app.backend.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public abstract class Message {
    private Long id;
    private String text;
    private Date date;
    private Long categoryId;
    private Long userId;

    public Message() {
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        this.date = date;
    }
}
