package com.team.app.backend.service;

import com.team.app.backend.persistance.model.Quiz;

import java.util.List;

public interface QuizService {

    List<Quiz> getAllQuizes();

    Quiz getQuiz(Long id);
}
