package com.team.app.backend.service;

import com.team.app.backend.persistance.model.Setting;
import com.team.app.backend.persistance.model.UserActivity;

import java.util.List;

public interface UserActivityService {
    void createUserActivity(UserActivity userActivity);
    UserActivity  getUserActivity(Long id);
    void updateUserActivity(UserActivity userActivity);
    void deleteUserActivity(Long id);
    List<UserActivity>  getFriendsActivities(Long user_id);
    List<Setting> getActivitiesSettings(Long user_id);
    void setFriendActivitiesSetting(Setting setting);
    //void setAchievmentActivity(Long user_id);
}
