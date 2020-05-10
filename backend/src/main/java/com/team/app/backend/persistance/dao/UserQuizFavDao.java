package com.team.app.backend.persistance.dao;

public interface UserQuizFavDao {

    void makeFavorite(Long user_id, Long  quiz_id);

    void deleteFavorite(Long user_id, Long  quiz_id);

}
