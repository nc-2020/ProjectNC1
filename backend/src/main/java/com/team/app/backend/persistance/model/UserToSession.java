package com.team.app.backend.persistance.model;

public class UserToSession {

    private Long id;
    private Long session_id;
    private Long user_id;
    private int score;

    public UserToSession() {}

    public UserToSession(Long session_id, Long user_id) {
        this.session_id = session_id;
        this.user_id = user_id;
    }

    public int getScore() {
        return score;
    }

    public UserToSession setScore(int score) {
        this.score = score;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserToSession setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getSession_id() {
        return session_id;
    }

    public UserToSession setSession_id(Long session_id) {
        this.session_id = session_id;
        return this;
    }

    public Long getUser_id() {
        return user_id;
    }

    public UserToSession setUser_id(Long user_id) {
        this.user_id = user_id;
        return this;
    }
}
