package com.team.app.backend.persistance.dao;


import com.team.app.backend.persistance.model.Achievement;
import com.team.app.backend.persistance.model.UserAchievement;

import java.util.List;

public interface AchievementDao {
    List<UserAchievement> getUserAchievements(long userId);
    List<Achievement> getAchievements();
}
