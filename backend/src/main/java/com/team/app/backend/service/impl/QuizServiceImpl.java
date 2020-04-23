package com.team.app.backend.service.impl;

import com.team.app.backend.dto.QuestionAddDto;
import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.persistance.dao.QuestionDao;
import com.team.app.backend.persistance.dao.QuizDao;
import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.QuestionType;
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

    @Autowired
    private QuestionDao questionDao;

    //TO DO
    @Override
    public void addQuestion(QuestionAddDto questionAddDto) {
        Question quest=new Question();
        quest.setTime(questionAddDto.getTime());
        quest.setText(questionAddDto.getText());
        quest.setMax_points(questionAddDto.getMax_points());
        quest.setImage(questionAddDto.getImage());
        quest.setType(questionAddDto.getType());
        quest.setQuiz_id(questionAddDto.getQuiz_id());
        questionDao.save(quest);
    }

    @Override
    public List<Question> getQuizQuestion(Long id) {
        return questionDao.getQuizQusetions(id);
    }


    @Override
    public void addQuiz(QuizAddDto quizAddDto) {
        Quiz quiz = new Quiz();
        quiz.setTitle(quizAddDto.getTitle());
        quiz.setDescription(quizAddDto.getDescription());
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
