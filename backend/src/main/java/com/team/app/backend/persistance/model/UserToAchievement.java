package com.team.app.backend.persistance.model;

public class UserToAchievement {
    private Long userId;
    private Long achievementId;

    public UserToAchievement() {
    }

    public UserToAchievement(Long userId, Long achievementId) {
        this.userId = userId;
        this.achievementId = achievementId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Long achievementId) {
        this.achievementId = achievementId;
    }
}
