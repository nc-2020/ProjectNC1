package com.team.app.backend.rest;

import com.team.app.backend.dto.UserLoginDto;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @ResponseBody
    public User login(
            @RequestBody UserLoginDto userDto
    ) {
        System.out.println("login");
        if (userService.isUserRegistered(userDto.getUsername())) {
            //String pass = passwordEncoder.encode(userDto.getPassword());
            System.out.println(userDto.getPassword());
            if (userService.getUserPassword(userDto.getUsername())
                    .equals(userDto.getPassword())) {
                return userService.getUserByUsername(userDto.getUsername())
                        ;
//            } else {
//                return new ResponseEntity<>(
//                        "Wrong password",
//                        HttpStatus.CONFLICT
//                );
//            }
            }
        } else {
            return null;
        }
        return null;
//        else {
//            return new ResponseEntity<>(
//                    "There is no user with username '" +
//                            userDto.getUsername() +
//                            "' in the database.",
//                    HttpStatus.CONFLICT
//            );
//        }
//    }

    }

    //TO DO
    @PostMapping("/logout")
    public Map<String, Object> logout() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("content", "Hello World");
        return model;

    }

}
