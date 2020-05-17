package com.team.app.backend.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswer {
    private Long id;
    private Long userSes_id;
    private int points;
    private int time;
}
