package com.team.app.backend.service.impl;

import com.team.app.backend.persistance.dao.UserQuizFavDao;
import com.team.app.backend.service.UserQuizFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserQuizFavoriteServiceImpl implements UserQuizFavoriteService {

    @Autowired
    UserQuizFavDao userQuizFavDao;

    @Override
    public void addFavorite(Long user_id, Long quiz_id) {
        userQuizFavDao.makeFavorite(user_id,quiz_id);
    }

    @Override
    public void deleteFavorite(Long user_id, Long quiz_id) {
        userQuizFavDao.deleteFavorite(user_id,quiz_id);
    }


}
