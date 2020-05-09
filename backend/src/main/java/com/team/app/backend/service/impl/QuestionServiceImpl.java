package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.model.QuestionType;
import com.team.app.backend.service.QuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Override
    public QuestionType getQuestionTypeById(Long id) {
        return null;
    }
}
