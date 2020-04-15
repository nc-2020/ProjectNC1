package com.team.app.backend.rest;

import com.team.app.backend.dto.UserDto;
import com.team.app.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> login(
            @RequestBody UserDto userDto
            ) {

        if (userService.isUserRegistered(userDto)) {
            return new ResponseEntity<>(
                    "Hello, " + userDto.getUsername(),
                    HttpStatus.OK
            );
        }
        else {
            return new ResponseEntity<>(
                    "There is no user with email " +
                            userDto.getEmail() +
                            " in the database.",
                    HttpStatus.OK
            );
        }
    }

}
