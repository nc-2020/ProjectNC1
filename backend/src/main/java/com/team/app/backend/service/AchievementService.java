package com.team.app.backend.service;

import com.team.app.backend.persistance.model.Achievement;

import java.util.List;

public interface AchievementService {
    List<Achievement> getAchievements();
}
