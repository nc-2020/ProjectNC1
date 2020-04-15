package com.team.app.backend.service.impl;

import com.team.app.backend.exception.UserAlreadyExistsException;
import com.team.app.backend.dto.UserDto;
import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User registerNewUserAccount(UserDto userDto)
            throws UserAlreadyExistsException {

        User user = new User();

        user.setEmail(userDto.getEmail())
                .setUsername(userDto.getUsername())
                .setPassword(userDto.getPassword())
                .setEnabled(false);
        userDao.save(user);
        return user;
    }

    /**
     * checks if user exists in the database
     * @param userDto user data transfer object
     * @return true if user exists in the database; otherwise false
     */
    @Override
    public boolean isUserRegistered(UserDto userDto) {
        return userDao.findByEmail(userDto.getEmail()) != null;
    }
}