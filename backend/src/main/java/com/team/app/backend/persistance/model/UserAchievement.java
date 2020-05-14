package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAchievement {
    private String title;
    private Long quizAmount;
    private Long played;

    public UserAchievement(String title, Long quizAmount, Long played) {
        this.title = title;
        this.quizAmount = quizAmount;
        this.played = played;
    }
}
