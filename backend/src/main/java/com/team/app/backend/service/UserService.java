package com.team.app.backend.service;

import com.team.app.backend.dto.UserLoginDto;
import com.team.app.backend.exception.UserAlreadyExistsException;
import com.team.app.backend.dto.UserRegistrationDto;
import com.team.app.backend.persistance.model.User;

public interface UserService {

    void registerNewUserAccount(UserRegistrationDto userDto)
            throws UserAlreadyExistsException;

    boolean isUserRegistered(String username);

    String getUserPassword(String username);
}
