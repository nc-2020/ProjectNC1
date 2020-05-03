package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Session;
import com.team.app.backend.persistance.model.UserToSession;

public interface UserToSessionDao {

    UserToSession save(UserToSession session);

    UserToSession getById(Long id);

    UserToSession deleteById(Long id);

    UserToSession update(UserToSession session);

}
