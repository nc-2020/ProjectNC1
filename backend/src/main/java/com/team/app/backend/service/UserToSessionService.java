package com.team.app.backend.service;

import com.team.app.backend.dto.FinishedQuizDto;
import com.team.app.backend.persistance.model.Session;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.persistance.model.UserToSession;

import java.util.List;

public interface UserToSessionService {

    UserToSession createNewUserToSession(User user, Session session);

    List<UserToSession> getAllBySessionId(Long sessionId);

    void insertScore(FinishedQuizDto finishedQuizDto);

}
