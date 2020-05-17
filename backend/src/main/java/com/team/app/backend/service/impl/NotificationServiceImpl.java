package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.NotificationDao;
import com.team.app.backend.persistance.model.Notification;
import com.team.app.backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service()
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Transactional
    @Override
    public void create(Notification not) {

        notificationDao.create(not);
    }
    @Transactional
    @Override
    public void update(Notification not) {
        notificationDao.update(not);

    }
    @Transactional
    @Override
    public void delete(List<Notification> notifications) {
        for(Notification not : notifications){
            notificationDao.delete(not.getId());
        }
    }

    @Override
    public List<Notification> getAll (Long user_id) {
        return notificationDao.getAll(user_id);
    }


    @Override
    public List<Notification> getSetting(Long userId) {
        return notificationDao.getSetting(userId);
    }

    @Transactional
    @Override
    public void setSetting(Notification not) {
        this.notificationDao.setSetting(not);
    }
}
