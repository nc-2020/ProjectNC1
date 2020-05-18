package com.team.app.backend.persistance.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizCategory {
    private Long id;
    private String name;
    private String description;
}
