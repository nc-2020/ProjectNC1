package com.team.app.backend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull @NotEmpty
    private String username;
    @NotNull @NotEmpty
    private String email;
    @NotNull @NotEmpty
    private String password;
    @NotNull @NotEmpty
    private String matchingPassword;

    public String getUsername() {
        return username;
    }

    public UserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public UserDto setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
        return this;
    }
}
