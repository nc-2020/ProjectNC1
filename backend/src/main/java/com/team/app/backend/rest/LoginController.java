package com.team.app.backend.rest;

import com.team.app.backend.dto.UserLoginDto;
import com.team.app.backend.dto.UserRegistrationDto;
import com.team.app.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> login(
            @RequestBody UserLoginDto userDto
            ) {

        if (userService.isUserRegistered(userDto.getUsername())) {
            if (userService.getUserPassword(userDto.getUsername())
                    .equals(userDto.getPassword())) {
                return new ResponseEntity<>(
                        "Hello, " + userDto.getUsername(),
                        HttpStatus.OK
                );
            } else {
                return new ResponseEntity<>(
                        "Wrong password",
                        HttpStatus.CONFLICT
                );
            }
        }
        else {
            return new ResponseEntity<>(
                    "There is no user with username '" +
                            userDto.getUsername() +
                            "' in the database.",
                    HttpStatus.CONFLICT
            );
        }
    }

}
