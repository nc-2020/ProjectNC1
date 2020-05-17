package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.UserActivity;

import java.util.List;

public interface UserActivityDao {
    void create(UserActivity userActivity);
    UserActivity get(Long id);
    void update(UserActivity userActivity);
    void delete(Long id);
    List<UserActivity> getFriendsActivities(Long user_id);

}
