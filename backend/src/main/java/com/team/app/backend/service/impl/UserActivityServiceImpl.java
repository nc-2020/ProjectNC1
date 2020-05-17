package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.UserActivityDao;
import com.team.app.backend.persistance.model.UserActivity;
import com.team.app.backend.service.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    UserActivityDao userActivityDao;

    @Override
    public void createUserActivity(UserActivity userActivity) {
        userActivityDao.create(userActivity);
    }

    @Override
    public UserActivity getUserActivity(Long id) {
        return userActivityDao.get(id);
    }

    @Override
    public void updateUserActivity(UserActivity userActivity) {
        userActivityDao.update(userActivity);
    }

    @Override
    public void deleteUserActivity(Long id) {
        userActivityDao.delete(id);
    }

    @Override
    public List<UserActivity>  getFriendsActivities(Long user_id) {
        return userActivityDao.getFriendsActivities(user_id);
    }
}
