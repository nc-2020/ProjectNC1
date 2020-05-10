package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.AnnouncementDao;
import com.team.app.backend.persistance.dao.mappers.AnnouncementRowMapper;
import com.team.app.backend.persistance.dao.mappers.UserRowMapper;
import com.team.app.backend.persistance.model.Announcement;
import com.team.app.backend.persistance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private AnnouncementRowMapper announcementRowMapper = new AnnouncementRowMapper();

    public AnnouncementDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }




    @Override
    public void create(Announcement announcement) {
        jdbcTemplate.update(
                "INSERT INTO announcement(title, text, date, image, status_id, cat_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
                announcement.getTitle(),
                announcement.getText(),
                announcement.getDate(),
                announcement.getImage(),
                announcement.getStatusId(),
                announcement.getCategoryId(),
                announcement.getUserId()
        );
    }

    @Override
    public void update(Announcement announcement) {
        jdbcTemplate.update(
                "UPDATE announcement set title = ?, text = ?, date = ?, image = ?, status_id = ?, cat_id = ?, user_id = ? where id = ?",
                announcement.getTitle(),
                announcement.getText(),
                announcement.getDate(),
                announcement.getImage(),
                announcement.getStatusId(),
                announcement.getCategoryId(),
                announcement.getUserId(),
                announcement.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE from announcement where id = ?",
                id
        );
    }
    @Override
    public List<Announcement> getCreated() {
        return jdbcTemplate.query("select an.id, an.title, an.text, an.date, an.image, an.status_id, an.cat_id, an.user_id from announcement  an where status_id = 1"
                ,announcementRowMapper);

    }
    @Override
    public List<Announcement> getAll() {
        return jdbcTemplate.query("select an.id, an.title, an.text, an.date, an.image, an.status_id, an.cat_id, an.user_id from announcement  an where status_id <> 1 order by an.status_id desc, an.date desc;"
                ,announcementRowMapper);

    }
    @Override
    public void approve(Long id) {
        jdbcTemplate.update("UPDATE announcement set status_id = 2  where id = ?", id);
    }


    @Override
    public Announcement get(Long id) {
        return new Announcement();
    }



}
