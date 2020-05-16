package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Quiz;

import java.util.List;

public interface QuizDao {
    Quiz get(Long id);

    List<Quiz> getByUserId(Long id);

    List<Quiz> getApproved();

    List<Quiz> getApprovedForUser(Long user_id);

    List<Quiz> getFavoriteQuizes(Long user_id);

    List<Quiz> getSuggestion(Long user_id);

    List<Quiz> getCategoryQuizes(String category);

    List<Quiz> searchQuizes(String category, String searchstring);
	
	List<Quiz> searchQuizes(String searchstring);

    List<Quiz> getAll();

    Long save(Quiz quiz);

    void update(Quiz quiz);

    void delete(Long id);

    void approve(Long id);

    List<Quiz> getCreated();




}
