package com.team.app.backend.persistance.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserStatus {
    private Long id;
    private String name;

    public UserStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
