package com.team.app.backend.service;

import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.persistance.model.Quiz;

import java.util.List;

public interface QuizService {

    void addQuiz(QuizAddDto quizAddDto);

    List<Quiz> getAllQuizes();

    Quiz getQuiz(Long id);
}
