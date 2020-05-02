package com.team.app.backend.persistance.dao.mappers;


import com.team.app.backend.persistance.model.QuizCategory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QuizCategoryRowMapper implements RowMapper<QuizCategory> {
    @Override
    public QuizCategory mapRow(ResultSet resultSet, int rownumber) throws SQLException {
        return new QuizCategory((long) resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"))
                ;
    }
}