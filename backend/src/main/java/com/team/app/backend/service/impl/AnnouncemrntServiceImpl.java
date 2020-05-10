package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.AnnouncementDao;
import com.team.app.backend.persistance.dao.NotificationDao;
import com.team.app.backend.persistance.model.Announcement;
import com.team.app.backend.persistance.model.Notification;
import com.team.app.backend.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AnnouncemrntServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    @Autowired
    private NotificationDao notificationDao;

    public void createAnnouncement(Announcement announcement) {
        announcement.setCategoryId(1L);
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        announcement.setDate(date);
        announcementDao.create(announcement);
    }

    @Override
    public List<Announcement> getCreated() {
        return announcementDao.getCreated();
    }

    @Override
    public List<Announcement> getAll() {
        return announcementDao.getAll();
    }

    @Override
    public void approve(Announcement announcement) {
        Notification notification = new Notification();
        notification.setCategoryID(1L);
        notification.setUserId(announcement.getUserId());
        if(announcement.getStatusId() == 2) {
            announcementDao.approve(announcement.getId());
            notification.setText("Announcement approved!)");
        } else {
            notification.setText("Announcement not approved!");
            announcementDao.delete(announcement.getId());
        }
        notificationDao.create(notification);
    }

    public Announcement  getAnnouncement(Long id) {
       return new Announcement();
    }
    public void updateAnnouncement(Announcement announcement) {
        announcementDao.update(announcement);
    }
    public void deleteAnnouncement(Long id) {
        announcementDao.delete(id);
    }

}
