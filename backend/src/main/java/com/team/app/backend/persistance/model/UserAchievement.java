package com.team.app.backend.persistance.model;

public class UserAchievement {
    private String title;
    private Long quizAmount;
    private Long played;


    public UserAchievement() {
    }

    public UserAchievement(String title, Long quizAmount, Long played) {
        this.title = title;
        this.quizAmount = quizAmount;
        this.played = played;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getQuizAmount() {
        return quizAmount;
    }

    public void setQuizAmount(Long quizAmount) {
        this.quizAmount = quizAmount;
    }

    public Long getPlayed() {
        return played;
    }

    public void setPlayed(Long played) {
        this.played = played;
    }
}
