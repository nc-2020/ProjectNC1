package com.team.app.backend.service;

import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.persistance.model.Session;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.persistance.model.UserToSession;

import java.util.List;

public interface SessionService {

    Session newSessionForQuiz(Quiz quiz);

    Session updateSession(Session session);

    Session getSessionById(Long id);

    List<Session> getAllByQuizId(Long quizId);

}
