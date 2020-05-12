package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Notification;

import java.util.List;

public interface NotificationDao {
    void create(Notification not);
    void update(Notification not);
    void delete(Long id);
    void setSetting(Notification not);
    List<Notification> getSetting(Long userId);
    List<Notification> getAll (Long user_id);
}
