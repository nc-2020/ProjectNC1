package com.team.app.backend.rest;

import com.team.app.backend.exception.UserAlreadyExistsException;
import com.team.app.backend.dto.UserDto;
import com.team.app.backend.persistance.dao.UserDao;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/registration")
public class RegistrationController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    UserDao userDao;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUserAccount(
            @RequestBody UserDto userDto) {

        LOGGER.debug("Registering user account with information: {}", userDto);

        final User registered;
        try {
            registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>(
                    "User with email " + userDto.getEmail() + " already exists.",
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                registered.toString(),
                HttpStatus.OK
        );
    }

    @GetMapping("/resource")
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }

}
