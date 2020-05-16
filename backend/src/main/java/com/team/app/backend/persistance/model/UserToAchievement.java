package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserToAchievement {
    private Long userId;
    private Long achievementId;

    public UserToAchievement(Long userId, Long achievementId) {
        this.userId = userId;
        this.achievementId = achievementId;
    }

}
