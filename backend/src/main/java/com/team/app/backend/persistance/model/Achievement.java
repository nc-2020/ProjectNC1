package com.team.app.backend.persistance.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Achievement {
    private Long id;
    private String title;
    private Long amountOfQuizzes;
    private Long amountOfCreated;
    private byte[] icon;
    private Long creatorUserId;
    private Long categoryId;

    public Achievement(Long id, String title, Long amountOfQuizzes, Long amountOfCreated, byte[] icon, Long creatorUserId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.amountOfQuizzes = amountOfQuizzes;
        this.amountOfCreated = amountOfCreated;
        this.icon = icon;
        this.creatorUserId = creatorUserId;
        this.categoryId = categoryId;
    }
}
