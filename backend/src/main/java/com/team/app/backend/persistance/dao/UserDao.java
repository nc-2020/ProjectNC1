package com.team.app.backend.persistance.dao;

import com.team.app.backend.persistance.model.User;

public interface UserDao {

    void save(User user);

    void update(User user);

    void delete(Long id);

    User get(Long id);

}
