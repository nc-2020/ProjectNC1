package com.team.app.backend.persistance.dao.mappers;

import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.persistance.model.QuizStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizRowMapper implements RowMapper<Quiz> {
    @Override
    public Quiz mapRow(ResultSet resultSet, int rownumber) throws SQLException {
        return new Quiz((long) resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getDate("date"),
                resultSet.getString("description"),
                resultSet.getBytes("image"),
                new QuizStatus((long)resultSet.getInt("role_id"),resultSet.getString("cat_name")),
                (long) resultSet.getInt("user_id"));
    }
}