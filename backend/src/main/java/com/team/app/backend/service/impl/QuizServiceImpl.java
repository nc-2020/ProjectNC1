package com.team.app.backend.service.impl;

import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.persistance.dao.QuizDao;
import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.persistance.model.QuizStatus;
import com.team.app.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao quizDao;



    @Override
    public void addQuiz(QuizAddDto quizAddDto) {
        Quiz quiz = new Quiz();
        quiz.setTitle(quizAddDto.getTitle());
        quiz.setDiscription(quizAddDto.getDescription());
        quiz.setDate(new Date());
        quiz.setImage(quizAddDto.getImage());
        quiz.setStatus_Id(new QuizStatus( 1L,"CREATED"));
        quizDao.save(quiz);
    }


    @Override
    public List<Quiz> getAllQuizes() {
        List<Quiz> list =  quizDao.getAll();
        System.out.println(list.size());
        return list;
    }

    @Override
    public Quiz getQuiz(Long id) {
        return quizDao.get(id);
    }
}
