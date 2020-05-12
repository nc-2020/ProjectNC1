package com.team.app.backend.persistance.model;

public class UserAnswer {

    private Long id;
    private Long userSes_id;
    private int points;
    private int time;

    public UserAnswer() {}

    public UserAnswer(Long id, Long userSes_id, int points, int time) {
        this.id = id;
        this.userSes_id = userSes_id;
        this.points = points;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public UserAnswer setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserSes_id() {
        return userSes_id;
    }

    public UserAnswer setUserSes_id(Long userSes_id) {
        this.userSes_id = userSes_id;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public UserAnswer setPoints(int points) {
        this.points = points;
        return this;
    }

    public int getTime() {
        return time;
    }

    public UserAnswer setTime(int time) {
        this.time = time;
        return this;
    }
}
