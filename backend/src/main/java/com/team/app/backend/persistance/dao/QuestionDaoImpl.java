package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.QuestionType;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class QuestionDaoImpl implements QuestionDao {

    private JdbcTemplate jdbcTemplate;
    public QuestionDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public QuestionType getType(Long id) {

        return null;
    }

    @Override
    public Question get(Long id) {
        return null;
    }

    @Override
    public void save(Question question) {

    }

    @Override
    public void update(Question question) {

    }

    @Override
    public void delete(Long id) {

    }
}
