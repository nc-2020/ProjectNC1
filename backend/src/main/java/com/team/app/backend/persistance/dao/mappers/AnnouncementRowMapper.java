package com.team.app.backend.persistance.dao.mappers;

import com.team.app.backend.persistance.model.Announcement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnouncementRowMapper implements RowMapper<Announcement> {

    @Override
    public Announcement mapRow(ResultSet resultSet, int rownumber) throws SQLException {
        return new Announcement(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("text"),
                resultSet.getDate("date"),
                resultSet.getBytes("image"),
                resultSet.getLong("status_id"),
                resultSet.getLong("cat_id"),
                resultSet.getLong("user_id"));
    }
}
