package com.team.app.backend.service;

public interface UserQuizFavoriteService {

    void addFavorite(Long user_id,Long quiz_id);

    void deleteFavorite(Long user_id,Long quiz_id);
}
