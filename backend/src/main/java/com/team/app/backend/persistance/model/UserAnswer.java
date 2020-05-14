package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAnswer {

    private Long id;
    private Long userSes_id;
    private int points;
    private int time;

    public UserAnswer(Long id, Long userSes_id, int points, int time) {
        this.id = id;
        this.userSes_id = userSes_id;
        this.points = points;
        this.time = time;
    }
}
