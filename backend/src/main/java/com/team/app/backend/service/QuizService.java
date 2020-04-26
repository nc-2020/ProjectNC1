package com.team.app.backend.service;

import com.team.app.backend.dto.questions.QuestionDefAddDto;
import com.team.app.backend.dto.questions.QuestionDto;
import com.team.app.backend.dto.questions.QuestionOptAddDto;
import com.team.app.backend.dto.questions.QuestionSeqAddDto;
import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.Quiz;

import java.util.List;

public interface QuizService {
    long addQuestion(QuestionDto questionDto);

    void addDefQuestion(QuestionDefAddDto questionDefAddDto);

    void addOptQuestion(QuestionOptAddDto questionOptAddDto);

    void addSeqOptQuestion(QuestionSeqAddDto questionSeqAddDto);

    void deleteQuiz(Long id);

    void deleteQuestion(Long id);

    List<Question> getQuizQuestion(Long id);

    Question getQuestion(Long id);

    Long addQuiz(QuizAddDto quizAddDto);

    List<Quiz> getAllQuizes();

    Quiz getQuiz(Long id);
}
