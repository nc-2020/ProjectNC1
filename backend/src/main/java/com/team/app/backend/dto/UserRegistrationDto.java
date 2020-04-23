package com.team.app.backend.dto;

public class UserRegistrationDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;

    public String getFirstname() {
        return firstName;
    }

    public UserRegistrationDto setFirstname(String firstname) {
        this.firstName = firstname;
        return this;
    }

    public String getLastname() {
        return lastName;
    }

    public UserRegistrationDto setLastname(String lastname) {
        this.lastName = lastname;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
