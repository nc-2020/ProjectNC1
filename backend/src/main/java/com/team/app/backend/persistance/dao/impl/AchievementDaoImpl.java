package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.AchievementDao;
import com.team.app.backend.persistance.dao.mappers.AchievementRowMapper;
import com.team.app.backend.persistance.model.Achievement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class AchievementDaoImpl implements AchievementDao {
    private final JdbcTemplate jdbcTemplate;

    private AchievementRowMapper achievementRowMapper = new AchievementRowMapper();

    public AchievementDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Achievement> getAchievements() {
        return jdbcTemplate.query("SELECT id, title, image, quiz_amount, created_amount, user_id, cat_id FROM achievement", achievementRowMapper);
    }
}
