package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.QuizCategory;

import java.util.List;

public interface QuizCategoryDao {
    void create(QuizCategory announcement);
    QuizCategory  get(Long id);
    List<QuizCategory> getAll();
    void update(QuizCategory announcement);
    void delete(Long id);

    void addQuizToCategory(Long quiz_id,Long cat_id);
}
