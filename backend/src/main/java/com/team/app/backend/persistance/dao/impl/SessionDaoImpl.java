package com.team.app.backend.persistance.dao.impl;

import com.team.app.backend.persistance.dao.SessionDao;
import com.team.app.backend.persistance.model.Session;
import com.team.app.backend.persistance.model.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class SessionDaoImpl implements SessionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Session save(Session session) {
        String sql = "INSERT INTO session(access_code, date, quiz_id, status_id) VALUES ( ?, ?, ? ,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            sql,
                            new String[] {"id"}
                    );
                    ps.setString(1, session.getAccessCode());
                    ps.setDate(2, session.getDate());
                    ps.setLong(3, session.getQuizId());
                    ps.setLong(4, session.getStatus().getId());
                    return ps;
                },
                keyHolder
        );
        return getById(keyHolder.getKey().longValue());
    }

    @Override
    public Session getById(Long id) {
        String sql = "SELECT S.id,S.access_code,S.date,S.quiz_id,S.status_id ,SS.name AS status_name FROM session S INNER JOIN ses_status SS ON S.status_id = SS.id WHERE S.id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    Session session = new Session();
                    session
                            .setId(resultSet.getLong("id"))
                            .setAccessCode(resultSet.getString("access_code"))
                            .setDate(resultSet.getDate("date"))
                            .setQuizId(resultSet.getLong("quiz_id"))
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
        String sql = "UPDATE session SET access_code = ?, date = ?, quiz_id = ? WHERE id = ?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            sql,
                            new String[] {"id"}
                    );
                    ps.setString(1, session.getAccessCode());
                    ps.setDate(2, session.getDate());
                    ps.setLong(3, session.getQuizId());
                    ps.setLong(4, session.getId());
                    return ps;
                },
                keyHolder
        );
        return getById(keyHolder.getKey().longValue());
    }

    @Override
    public void setSesionStatus(Long ses_id, Long status_id) {
        jdbcTemplate.update("UPDATE session SET status_id =? WHERE id = ?",
                new Object[]{status_id,ses_id}
        );
    }


}
