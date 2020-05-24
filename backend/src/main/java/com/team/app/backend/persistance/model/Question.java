package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class Question {
    private Long id;
    private Integer time;
    private String text;
    private Integer max_points;
    private String image;
    private QuestionType type;
    private Long quiz_id;


    public Question(Long id, Integer time, String text, Integer max_points, String image, QuestionType type, Long quiz_id) {
        this.id = id;
        this.time = time;
        this.text = text;
        this.max_points = max_points;
        this.image = image;
        this.type = type;
        this.quiz_id = quiz_id;
    }

}
