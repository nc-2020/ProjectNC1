package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.AchievementDao;
import com.team.app.backend.persistance.dao.mappers.AchievementRowMapper;
import com.team.app.backend.persistance.model.Achievement;
import com.team.app.backend.persistance.model.UserAchievement;
import com.team.app.backend.persistance.model.UserInvite;
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
    public List<UserAchievement> getAchievements(long userId) {
        return jdbcTemplate.query("SELECT A.title, A.quiz_amount, COUNT(QTC.cat_id) AS played FROM user_to_ses US \n" +
                "INNER JOIN session S ON US.ses_id = S.id\n" +
                "INNER JOIN quiz_to_categ QTC ON QTC.quiz_id = S.quiz_id\n" +
                "INNER JOIN achievement A ON A.id = QTC.cat_id\n" +
                "WHERE US.user_id = ?\n" +
                "GROUP BY QTC.cat_id, A.title, A.quiz_amount", new Object[] { userId },
                (resultSet, i) -> {
                    UserAchievement userAchievement = new UserAchievement();
                    userAchievement.setTitle(resultSet.getString("title"));
                    userAchievement.setQuizAmount(resultSet.getLong("quiz_amount"));
                    userAchievement.setPlayed(resultSet.getLong("played"));
                    return userAchievement;
                });
    }
}
