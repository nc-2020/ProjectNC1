package com.team.app.backend.rest;


import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.persistance.model.User;
import com.team.app.backend.service.QuizService;
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

    @Autowired
    QuizService quizService;



    //TO DO
    @PostMapping("/quiz")
    public ResponseEntity<String> registerUserAccount(
            @RequestBody QuizAddDto quizDto) {

        return new ResponseEntity<>(
                "Quiz was created!",
                HttpStatus.OK
        );
    }


    @GetMapping("/search")
    public List<User> searchUser(@RequestBody  String searchString) {
        List<User> list = userService.searchUsers(searchString);
        return userService.searchUsers(searchString);
    }

    @GetMapping("/quiz/{id}")
    public Quiz quiz( @PathVariable("id") long id) {
        System.out.println(id);
        return quizService.getQuiz(id);
    }

    @GetMapping("/quiz")
    public List<Quiz> quizes() {
       return quizService.getAllQuizes();
    }
}
