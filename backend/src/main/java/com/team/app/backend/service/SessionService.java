package com.team.app.backend.service;

import com.team.app.backend.dto.SessionStatsDto;
import com.team.app.backend.persistance.model.*;

import java.util.List;

public interface SessionService {

    Session newSessionForQuiz(Quiz quiz);

    Session updateSession(Session session);

    Session getSessionById(Long id);

    List<Session> getAllByQuizId(Long quizId);

    void setSesionStatus(Long ses_id,SessionStatus sessionStatus);

    Session getSessionByAccessCode(String access_code);
}
