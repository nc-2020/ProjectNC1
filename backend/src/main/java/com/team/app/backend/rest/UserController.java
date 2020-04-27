package com.team.app.backend.rest;


import com.team.app.backend.dto.QuestionAddDto;
import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.dto.UserCreateDto;
import com.team.app.backend.dto.UserUpdateDto;
import com.team.app.backend.persistance.model.Question;
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

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    QuizService quizService;



    @PostMapping("/quiz")
    public void createMewQuiz(
            @RequestBody QuizAddDto quizDto) {
        quizService.addQuiz(quizDto);
    }


    @PostMapping("/question")
    public void createNewQuestion(
            @RequestBody QuestionAddDto questionAddDto) {
        System.out.println("add question");
        quizService.addQuestion(questionAddDto);
    }

    @GetMapping("/user/search/{name}")
    public List<User> searchUser(@PathVariable("name") String name) {
        List<User> list = userService.searchUsers(name);
        return userService.searchUsers(name);
    }

    @GetMapping("/quiz/quest/{id}")
    public List<Question> questions(@PathVariable("id") long id) {
        return quizService.getQuizQuestion(id);
    }

    @GetMapping("/quiz/{id}")
    public Quiz quiz( @PathVariable("id") long id) {
        //System.out.println(id);
        return quizService.getQuiz(id);
    }


    @PutMapping("/user/update")
    public User createUser(
                           @RequestBody UserUpdateDto userUpdateDto) {
        System.out.println(userUpdateDto.getFirstName()+"   "+userUpdateDto.getLastName());
        return userService.updateUser(userUpdateDto);
    };


    @PostMapping("user/create")
    public User createUser(
            @RequestBody UserCreateDto userDto){
        return userService.createNewUser(userDto);
    }

    @DeleteMapping("user/delete/{id}")
    public Map<String,Object> deleteUser( @PathVariable("id") long id){
        Map<String, Object> model = new HashMap<String, Object>();
        if(userService.deleteUser(id)){

            model.put("message", "User was deleted");
        }else{
            model.put("message", "Exception while deleted");
        }
            //{
//            return new ResponseEntity<>(
//                    "User was deleted!",
//                    HttpStatus.OK
//            );
//        }else{
//            return new ResponseEntity<>(
//                    "Some error while deleting!",
//                    HttpStatus.CONFLICT);
//        }


        return model;
    }

    @GetMapping("/quiz")
    public List<Quiz> quizes() {
       return quizService.getAllQuizes();
    }
}
