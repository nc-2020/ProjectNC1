package com.team.app.backend.dto;

public class UserAnswerDto {

    private long userSes_id;
    private int points;
    private int time;

    public UserAnswerDto() {}

    public UserAnswerDto(long userSes_id, int points, int time) {
        this.userSes_id = userSes_id;
        this.points = points;
        this.time = time;
    }

    public long getUserSes_id() {
        return userSes_id;
    }

    public UserAnswerDto setUserSes_id(long userSes_id) {
        this.userSes_id = userSes_id;
        return this;
    }

    public int getPoints() {
        return points;
    }

    public UserAnswerDto setPoints(int points) {
        this.points = points;
        return this;
    }

    public int getTime() {
        return time;
    }

    public UserAnswerDto setTime(int time) {
        this.time = time;
        return this;
    }
}
