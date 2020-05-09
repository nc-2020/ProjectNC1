package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Session;
import com.team.app.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;

@Repository
public class SessionDaoImpl implements SessionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Session save(Session session) {
        String sql = "INSERT INTO session(access_code, date, quiz_id) VALUES ( ?, ?, ? )";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            sql,
                            new String[] {"id"}
                    );
                    ps.setString(1, session.getAccess_code());
                    ps.setDate(2, (Date) session.getDate());
                    ps.setLong(3, session.getQuiz_id());
                    return ps;
                },
                keyHolder
        );
        return getById((Long) keyHolder.getKey());
    }

    @Override
    public Session getById(Long id) {
        String sql = "SELECT * FROM session WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    Session session = new Session();
                    session
                            .setId(resultSet.getLong("id"))
                            .setAccess_code(resultSet.getString("access_code"))
                            .setDate(resultSet.getDate("date"))
                            .setQuiz_id(resultSet.getLong("quiz_id"));
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
                    ps.setString(1, session.getAccess_code());
                    ps.setDate(2, (Date) session.getDate());
                    ps.setLong(3, session.getQuiz_id());
                    ps.setLong(4, session.getId());
                    return ps;
                },
                keyHolder
        );
        return getById((Long) keyHolder.getKey());
    }
}
