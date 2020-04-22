package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.QuizDao;
import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao quizDao;

    @Override
    public List<Quiz> getAllQuizes() {
        return null;
    }

    @Override
    public Quiz getQuiz(Long id) {
        return null;
    }
}
