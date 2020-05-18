package com.team.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class RecoveryDto {
    private String email;

    public RecoveryDto() {
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RecoveryDto(String email) {

        this.email = email;
    }
}
