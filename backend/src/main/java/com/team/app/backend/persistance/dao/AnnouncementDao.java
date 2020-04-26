package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Announcement;

public interface AnnouncementDao {
    void create(Announcement announcement);
    Announcement  get(Long id);
    void update(Announcement announcement);
    void delete(Long id);
}
