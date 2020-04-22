package com.team.app.backend.rest;


import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.service.UserService;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/search")
    public List<User> searchUser(@RequestBody  String searchString) {
        List<User> list = userService.searchUsers(searchString);
        return userService.searchUsers(searchString);
    }

    //TO DO
    @GetMapping("/quiz/id")
    public Quiz quiz() {
        Quiz quiz = new Quiz();

        return quiz;
    }
    //TO DO
    @GetMapping("/quiz")
    public Map<String, Object> qui() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", "test");
        model.put("content", "Hello World");
        return model;
    }
}
