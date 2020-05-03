package com.team.app.backend.service;

import com.team.app.backend.persistance.model.QuizCategory;

import java.util.List;

public interface QuizCategoryService {

    void createQuizCategory(QuizCategory announcement);
    QuizCategory  getQuizCategory(Long id);
    void updateQuizCategory(QuizCategory announcement);
    void deleteQuizCategory(Long id);
    List<QuizCategory> getAllQuizCategories();
}
