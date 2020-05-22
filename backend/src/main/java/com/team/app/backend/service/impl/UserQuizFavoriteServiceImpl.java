package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.QuizDao;
import com.team.app.backend.persistance.dao.UserActivityDao;
import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.dao.UserQuizFavDao;
import com.team.app.backend.persistance.model.UserActivity;
import com.team.app.backend.service.UserQuizFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserQuizFavoriteServiceImpl implements UserQuizFavoriteService {

    @Autowired
    UserQuizFavDao userQuizFavDao;

    @Autowired
    UserDao userDao;

    @Autowired
    QuizDao quizDao;

    @Autowired
    UserActivityDao userActivityDao;

    @Override
    public void addFavorite(Long user_id, Long quiz_id) {
        long millis=System.currentTimeMillis();
        java.sql.Timestamp date=new java.sql.Timestamp(millis);

        UserActivity userActivity=new UserActivity();
        userActivity.setCategoryId(5L);
        userActivity.setDate(date);
        userActivity.setUserId(user_id);
        //userActivity.setText(String.format("%s liked quiz called \"%s\"",userDao.get(user_id).getUsername(),quizDao.get(quiz_id).getTitle()));
        userActivity.setElem_id(quiz_id);
        userActivityDao.create(userActivity);
        userQuizFavDao.makeFavorite(user_id,quiz_id);
    }

    @Override
    public void deleteFavorite(Long user_id, Long quiz_id) {
        userQuizFavDao.deleteFavorite(user_id,quiz_id);
    }


}
