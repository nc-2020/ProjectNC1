package com.team.app.backend.service;

import com.team.app.backend.exception.UserAlreadyExistsException;
import com.team.app.backend.dto.UserDto;
import com.team.app.backend.persistance.model.User;

public interface UserService {

    User registerNewUserAccount(UserDto userDto)
            throws UserAlreadyExistsException;

    boolean isUserRegistered(UserDto userDto);
}
