package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.SessionDao;
import com.team.app.backend.persistance.model.Session;
import com.team.app.backend.persistance.model.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class SessionDaoImpl implements SessionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    Environment env;

    @Override
    public Session save(Session session) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            env.getProperty("create.session"),
                            new String[] {"id"}
                    );
                    ps.setString(1, session.getAccessCode());
                    ps.setTimestamp(2, session.getDate());
                    ps.setLong(3, session.getQuiz_id());
                    ps.setLong(4, session.getStatus().getId());
                    return ps;
                },
                keyHolder
        );
        return getById(keyHolder.getKey().longValue());
    }

    @Override
    public Session getById(Long id) {
        return jdbcTemplate.queryForObject(
                env.getProperty("get.session"),
                new Object[]{id},
                (resultSet, i) -> {
                    Session session = new Session();
                    session
                            .setId(resultSet.getLong("id"))
                            .setAccessCode(resultSet.getString("access_code"))
                            .setDate(resultSet.getTimestamp("date"))
                            .setQuiz_id(resultSet.getLong("quiz_id"))

                            .setStatus(new SessionStatus(resultSet.getLong("status_id"),resultSet.getString("status_name")));
                    return session;
                });
    }

    @Override
    public Session deleteById(Long id) {
        return null;
    }

    @Override
    public Session update(Session session) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            env.getProperty("update.session"),
                            new String[] {"id"}
                    );
                    ps.setString(1, session.getAccessCode());
                    ps.setTimestamp(2, session.getDate());
                    ps.setLong(3, session.getQuiz_id());
                    ps.setLong(4, session.getId());
                    return ps;
                },
                keyHolder
        );
        return getById(keyHolder.getKey().longValue());
    }

    @Override
    public void setSesionStatus(Long ses_id, Long status_id) {

        jdbcTemplate.update(
                env.getProperty("update.status.session"),
                status_id,ses_id
        );
    }




    @Override
    public boolean checkAccesCodeAvailability(String access_code) {
        return jdbcTemplate.queryForObject(
                env.getProperty("check.access_code.session"),
                new Object[]{access_code},Boolean.class
        );
    }

    @Override
    public Session getSessionByCode(String access_code) {
        return jdbcTemplate.queryForObject(
                env.getProperty("get.session.by.access_code"),
                 new Object[]{access_code},
                (resultSet, i) -> {
                    Session session = new Session();
                    session
                            .setId(resultSet.getLong("id"))
                            .setAccessCode(resultSet.getString("access_code"))
                            .setDate(resultSet.getTimestamp("date"))
                            .setQuiz_id(resultSet.getLong("quiz_id"))
                            .setStatus(new SessionStatus(resultSet.getLong("status_id"),resultSet.getString("status_name")));
                    return session;
                });
    }


}
