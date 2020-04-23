package com.team.app.backend.service;

import com.team.app.backend.dto.QuestionAddDto;
import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.Quiz;

import java.util.List;

public interface QuizService {


    void addQuestion(QuestionAddDto questionAddDto);

    List<Question> getQuizQuestion(Long id);

    void addQuiz(QuizAddDto quizAddDto);

    List<Quiz> getAllQuizes();

    Quiz getQuiz(Long id);
}
