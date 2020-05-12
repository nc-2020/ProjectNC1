package com.team.app.backend.service.impl;

import com.team.app.backend.dto.FinishedQuizDto;
import com.team.app.backend.persistance.dao.UserToSessionDao;
import com.team.app.backend.persistance.model.Session;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.persistance.model.UserToSession;
import com.team.app.backend.service.UserToSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserToSessionServiceImpl implements UserToSessionService {

    @Autowired
    UserToSessionDao userToSessionDao;

    @Override
    public UserToSession createNewUserToSession(User user, Session session) {
        UserToSession userToSession = new UserToSession(user.getId(), session.getId());
        System.out.println("user"+user.getId()+"ses"+session.getId());
        return userToSessionDao.save(userToSession);
    }

    @Override
    public List<UserToSession> getAllBySessionId(Long sessionId) {
        return userToSessionDao.getAllBySes(sessionId);
    }

    @Override
    public void insertScore(FinishedQuizDto finishedQuizDto) {
        UserToSession userToSession = new UserToSession();
        userToSession.setScore(finishedQuizDto.getScore())
                .setSession_id(finishedQuizDto.getSes_id())
                .setUser_id(finishedQuizDto.getUser_id());
        userToSessionDao.update(userToSession);
    }
}
