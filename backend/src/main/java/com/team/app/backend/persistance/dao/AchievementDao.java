package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Achievement;

import java.util.List;

public interface AchievementDao {
    List<Achievement> getAchievements();
}
