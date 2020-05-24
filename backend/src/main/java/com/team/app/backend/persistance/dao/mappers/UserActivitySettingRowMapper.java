package com.team.app.backend.persistance.dao.mappers;

import com.team.app.backend.persistance.model.Setting;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserActivitySettingRowMapper  implements RowMapper<Setting> {
    @Override
    public Setting mapRow(ResultSet resultSet, int rownumber) throws SQLException {
        return new Setting(
                resultSet.getLong("act_cat"),
                resultSet.getLong("user_id"),
                resultSet.getBoolean("enabled")
        );
    }
}
