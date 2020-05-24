package com.team.app.backend.persistance.model;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public abstract class Message {
    private Long id;
    private String text;
    private Timestamp date;
    private Long categoryId;
    private Long userId;


    public Message() {
        long millis=System.currentTimeMillis();
        java.sql.Timestamp date = new java.sql.Timestamp(millis);
        this.date = date;
    }


}
