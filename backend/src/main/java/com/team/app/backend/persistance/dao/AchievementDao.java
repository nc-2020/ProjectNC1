package com.team.app.backend.persistance.dao;


import com.team.app.backend.persistance.model.UserAchievement;

import java.util.List;

public interface AchievementDao {
    List<UserAchievement> getAchievements(long userId);
}
