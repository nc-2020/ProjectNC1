package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.QuestionType;

import java.util.List;

public interface QuestionDao {
        QuestionType getType(Long id);

        List<Question> getQuizQusetions(Long id);

        Question get(Long id);

        void save(Question question);

        void update(Question question);

        void delete(Long id);
}
