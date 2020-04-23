package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.dao.mappers.QuestionRowMapper;
import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.QuestionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class QuestionDaoImpl implements QuestionDao {

    private JdbcTemplate jdbcTemplate;
    public QuestionDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private QuestionRowMapper questionRowMapper=new QuestionRowMapper();


    @Override
    public QuestionType getType(Long id) {

        return null;
    }

    @Override
    public List<Question> getQuizQusetions(Long id) {
            return jdbcTemplate.query("SELECT Q.id, Q.time, Q.text, Q.max_points, Q.image, Q.type_id, QT.name AS type_name, Q.quiz_id FROM question Q INNER JOIN quest_type QT ON Q.type_id=QT.id where Q.quiz_id = ? "
                    ,new Object[] { id },
                    questionRowMapper);

    }

    @Override
    public Question get(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT Q.id, Q.time, Q.text, Q.max_points, Q.image, Q.type_id, QT.name AS type_name, Q.quiz_id FROM question Q INNER JOIN quest_type QT ON Q.type_id=QT.id where Q.id = ?",
                new Object[]{id},
                questionRowMapper
        );

    }

    @Override
    public void save(Question question) {
        jdbcTemplate.update(
                "INSERT INTO question( time, text, max_points, image, type_id, quiz_id) VALUES ( ?, ?, ?, ?, ?, ?)",
                question.getTime(),
                question.getText(),
                question.getMax_points(),
                question.getImage(),
                question.getType().getId(),
                question.getQuiz_id()
        );
    }

    @Override
    public void update(Question question) {
        jdbcTemplate.update(
                "UPDATE public.question SET time =?, text=?, max_points=?, image=?, type_id=?, quiz_id=? WHERE id = ?",
                question.getTime(),
                question.getText(),
                question.getMax_points(),
                question.getImage(),
                question.getType().getId(),
                question.getQuiz_id(),
                question.getId()
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE from question where id = ?",
                id
        );
    }
}
