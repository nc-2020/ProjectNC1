package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.Session;

public interface SessionDao {

    Session save(Session session);

    Session getById(Long id);

    Session deleteById(Long id);

    Session update(Session session);

}
