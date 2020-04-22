package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.QuestionType;

public interface QuestionDao {
        QuestionType getType(Long id);

        Question get(Long id);

        void save(Question question);

        void update(Question question);

        void delete(Long id);
}
