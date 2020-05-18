package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.UserActivityDao;
import com.team.app.backend.persistance.dao.mappers.UserActivityRowMapper;
import com.team.app.backend.persistance.model.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserActivityDaoImpl implements UserActivityDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    Environment env;

    private UserActivityRowMapper userActivityRowMapper = new UserActivityRowMapper();

    public UserActivityDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(UserActivity userActivity) {
        jdbcTemplate.update(
                env.getProperty("create.activity"),
                userActivity.getText(),
                userActivity.getDate(),
                userActivity.getCategoryId(),
                userActivity.getUserId()
        );
    }

    @Override
    public UserActivity get(Long id) {
        return jdbcTemplate.queryForObject(env.getProperty("get.activity"),
                new Object[]{id}
                ,userActivityRowMapper);
    }

    @Override
    public void update(UserActivity userActivity) {
        jdbcTemplate.update(
                env.getProperty("update.activity"),
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
                env.getProperty("delete.activity"),id
        );
    }

    @Override
    public List<UserActivity> getFriendsActivities(Long user_id) {
        return jdbcTemplate.query(env.getProperty("get.friends.activities"),
                new Object[]{user_id,user_id}
                ,userActivityRowMapper);
    }
}
