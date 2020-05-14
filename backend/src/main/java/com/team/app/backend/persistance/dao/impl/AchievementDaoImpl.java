package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.AchievementDao;
import com.team.app.backend.persistance.dao.mappers.AchievementRowMapper;
import com.team.app.backend.persistance.model.Achievement;
import com.team.app.backend.persistance.model.UserAchievement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;



import javax.sql.DataSource;
import java.util.List;





@PropertySource("classpath:sql_query.properties")
@Repository
public class AchievementDaoImpl implements AchievementDao {
    private final JdbcTemplate jdbcTemplate;
    @Value("${achievement.all}")
    private String sqlGetAchievements;
    @Value("${achievement.user}")
    private String sqlGetUserAchievements;


    private AchievementRowMapper achievementRowMapper = new AchievementRowMapper();

    public AchievementDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<UserAchievement> getUserAchievements(long userId) {
        System.out.println(sqlGetUserAchievements);
        return jdbcTemplate.query(sqlGetUserAchievements, new Object[] { userId },
                (resultSet, i) -> {
                    UserAchievement userAchievement = new UserAchievement();
                    userAchievement.setTitle(resultSet.getString("title"));
                    userAchievement.setQuizAmount(resultSet.getLong("quiz_amount"));
                    userAchievement.setPlayed(resultSet.getLong("played"));
                    return userAchievement;
                });
    }

    @Override
    public List<Achievement> getAchievements() {
        return jdbcTemplate.query(sqlGetAchievements, achievementRowMapper);
    }
}
