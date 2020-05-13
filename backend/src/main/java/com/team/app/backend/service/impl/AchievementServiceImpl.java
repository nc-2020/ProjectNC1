package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.AchievementDao;
import com.team.app.backend.persistance.model.Achievement;
import com.team.app.backend.persistance.model.UserAchievement;
import com.team.app.backend.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementDao achievementDao;

    @Override
    public List<UserAchievement> getAchievements(long id) {
        return achievementDao.getAchievements(id);
    }
}
