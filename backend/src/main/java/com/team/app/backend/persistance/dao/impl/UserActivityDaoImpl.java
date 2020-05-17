package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.UserActivityDao;
import com.team.app.backend.persistance.dao.mappers.UserActivityRowMapper;
import com.team.app.backend.persistance.model.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserActivityDaoImpl implements UserActivityDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private UserActivityRowMapper userActivityRowMapper = new UserActivityRowMapper();

    public UserActivityDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(UserActivity userActivity) {
        jdbcTemplate.update(
                "INSERT INTO user_activ( text, date, cat_id, user_id) VALUES (?, ?, ?, ?)",
                userActivity.getText(),
                userActivity.getDate(),
                userActivity.getCategoryId(),
                userActivity.getUserId()
        );
    }

    @Override
    public UserActivity get(Long id) {
        String sql ="SELECT id, date, cat_id, user_id, text FROM public.user_activ WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id}
                ,userActivityRowMapper);
    }

    @Override
    public void update(UserActivity userActivity) {
        jdbcTemplate.update(
                "UPDATE user_activ set text = ?, date = ?,  cat_id = ?, user_id = ? where id = ?",
                userActivity.getText(),
                userActivity.getDate(),
                userActivity.getCategoryId(),
                userActivity.getUserId(),
                userActivity.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE from user_activ where id = ?",
                id
        );
    }

    @Override
    public List<UserActivity> getFriendsActivities(Long user_id) {
        String sql ="SELECT id, date, cat_id, user_id, text FROM public.user_activ \n" +
                "WHERE user_id IN (SELECT user_id_from\n" +
                "                  FROM friend_to\n" +
                "                  WHERE user_id_to = ?)\n" +
                "     OR user_id IN (SELECT user_id_to\n" +
                "                    FROM friend_to\n" +
                "                    WHERE user_id_from = ?)\n" +
                "ORDER BY date DESC";
        return jdbcTemplate.query(sql,new Object[]{user_id,user_id}
                ,userActivityRowMapper);
    }
}
