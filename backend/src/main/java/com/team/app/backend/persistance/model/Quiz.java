package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class Quiz {
    private Long id;
    private String title;
    private Date date;
    private String description;
    private String image;
    private QuizStatus status;
    private Long user_id;
    private String quiz_author;
    private Boolean favorite;
    private Boolean completed;


    public Quiz(Long id, String title, Date date, String description, String image, QuizStatus status, Long user_id) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.image = image;
        this.status = status;
        this.user_id = user_id;
    }
}
