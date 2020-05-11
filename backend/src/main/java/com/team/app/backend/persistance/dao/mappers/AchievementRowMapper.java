package com.team.app.backend.persistance.dao.mappers;

import com.team.app.backend.persistance.model.Achievement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AchievementRowMapper implements RowMapper<Achievement> {
    @Override
    public Achievement mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Achievement(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getLong("quiz_amount"),
                resultSet.getLong("created_amount"),
                resultSet.getBytes("image"),
                resultSet.getLong("user_id"),
                resultSet.getLong("cat_id")
        );
    }
}
