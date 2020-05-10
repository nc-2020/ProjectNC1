package com.team.app.backend.persistance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserQuizFavDaoImpl implements UserQuizFavDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    public UserQuizFavDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void makeFavorite(Long user_id, Long quiz_id) {
        jdbcTemplate.update(
                "INSERT INTO user_quiz_fav(user_id, quiz_id) VALUES (?, ?)",
                user_id, quiz_id);
    }

    @Override
    public void deleteFavorite(Long user_id, Long quiz_id) {
        System.out.println(user_id+" "+quiz_id);
        jdbcTemplate.update(
                "DELETE from user_quiz_fav where user_id = ? AND quiz_id = ?",
                user_id,quiz_id
        );
    }
}
