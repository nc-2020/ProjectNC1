package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.NotificationDao;
import com.team.app.backend.persistance.dao.mappers.NotificationRowMapper;
import com.team.app.backend.persistance.model.Notification;
import com.team.app.backend.persistance.model.UserInvite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

@Repository
public class NotificationDaoImpl implements NotificationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NotificationRowMapper notificationRowMapper = new NotificationRowMapper();

    public NotificationDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void create(Notification not) {

        jdbcTemplate.update("INSERT INTO notification( text, seen, date, cat_id, user_id) values( ?, ?, ?, ?, ?)",
                not.getText(),
                not.isSeen(),
                not.getDate(),
                not.getCategoryId(),
                not.getUserId());
    }
    @Override
    public void update(Notification not) {
        jdbcTemplate.update(
                "UPDATE notification set  text = ?, seen = ?, date = ?,  cat_id = ?, user_id = ? where id = ?",
                not.getText(),
                not.isSeen(),
                not.getDate(),
                not.getCategoryId(),
                not.getUserId(),
                not.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE from notification where id = ?",
                id
        );
    }
    @Override
    public void setSetting(Notification not) {
        jdbcTemplate.update("INSERT INTO not_setting(cat_id, user_id, enabled) values( ?, ?, ?) ON CONFLICT ON CONSTRAINT not_setting_pkey DO UPDATE set enabled = ?",
                not.getCategoryId(),
                not.getUserId(),
                not.isSeen(),
                not.isSeen());
    }
    public List<Notification> getSetting(Long userId) {
        return jdbcTemplate.query(
                "select nt.cat_id, nt.user_id, nt.enabled from not_setting nt where nt.user_id = ? order by nt.cat_id",
                new Object[]{userId}
                ,(resultSet, i) -> {
                    Notification not = new Notification();
                    not.setCategoryId(resultSet.getLong("cat_id"));
                    not.setUserId(resultSet.getLong("user_id"));
                    not.setSeen(resultSet.getBoolean("enabled"));
                    return not;
                });

    }

    @Override
    public List<Notification> getAll (Long user_id) {

        return jdbcTemplate.query(
                "select nt.id, nt.text, nt.seen, nt.date, nt.cat_id, nt.user_id from notification nt where nt.cat_id not in (select setting.cat_id from public.not_setting setting where setting.enabled = false and setting.user_id = ?) and nt.user_id = ? order by nt.date desc",
                new Object[]{user_id,user_id}
                ,notificationRowMapper);
    }
}
