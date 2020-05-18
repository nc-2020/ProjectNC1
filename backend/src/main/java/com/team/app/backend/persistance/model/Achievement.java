package com.team.app.backend.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Achievement {
    private Long id;
    private String title;
    private Long amountOfQuizzes;
    private Long amountOfCreated;
    private byte[] icon;
    private Long creatorUserId;
    private Long categoryId;
}
