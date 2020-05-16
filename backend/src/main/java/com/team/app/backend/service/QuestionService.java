package com.team.app.backend.service;

import com.team.app.backend.persistance.model.QuestionType;

public interface QuestionService {

    QuestionType getQuestionTypeById(Long id);

}
