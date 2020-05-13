package com.team.app.backend.service;

import com.team.app.backend.persistance.model.Achievement;
import com.team.app.backend.persistance.model.UserAchievement;

import java.util.List;

public interface AchievementService {
    List<UserAchievement> getAchievements(long id);
}
