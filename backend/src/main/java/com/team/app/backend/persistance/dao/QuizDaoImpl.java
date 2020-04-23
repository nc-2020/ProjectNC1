package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.dao.mappers.QuizRowMapper;

import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.persistance.model.QuizStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class QuizDaoImpl implements QuizDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private QuizRowMapper quizRowMapper=new QuizRowMapper();

    public QuizDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Quiz get(Long id) {
        return jdbcTemplate.queryForObject(
                "select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id where Q.id = ?",
                new Object[]{id},
                quizRowMapper
        );
    }



    @Override
    public List<Quiz> getByUserId(Long id) {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id where user_id = ? "
                ,new Object[] { id },
                quizRowMapper);
    }

    @Override
    public List<Quiz> getAll() {
        return jdbcTemplate.query("select Q.id,Q.title,Q.date,Q.description,Q.image,Q.status_id,QS.name as status_name , Q.user_id from quiz Q INNER JOIN quiz_status QS ON QS.id = Q.status_id"
                ,quizRowMapper);
    }


    @Override
    public void save(Quiz quiz) {
        jdbcTemplate.update(
                "INSERT INTO quiz( title, date, description, image, status_id, user_id) VALUES (?, ?, ?, ?, ?, ?);",
                quiz.getTitle(),
                quiz.getDate(),
                quiz.getDescription(),
                quiz.getImage(),
                quiz.getStatus().getId(),
                quiz.getUser()
            );
    }

    @Override
    public void update(Quiz quiz) {
        jdbcTemplate.update(
                "UPDATE quiz set title = ?, date = ?, desription = ?, image = ?, status_id = ?, user_id = ?  where id = ?",
                quiz.getTitle(),
                quiz.getDate(),
                quiz.getDescription(),
                quiz.getImage(),
                quiz.getStatus().getId(),
                quiz.getUser(),
                quiz.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE from quiz where id = ?",
                id
        );
    }
}
