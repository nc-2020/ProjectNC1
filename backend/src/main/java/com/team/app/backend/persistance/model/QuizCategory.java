package com.team.app.backend.persistance.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuizCategory {

    private Long id;
    private String name;
    private String description;

    public QuizCategory(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
