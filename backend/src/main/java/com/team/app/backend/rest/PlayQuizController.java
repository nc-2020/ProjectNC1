package com.team.app.backend.rest;

import com.team.app.backend.dto.FinishedQuizDto;
import com.team.app.backend.persistance.model.*;
import com.team.app.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/quiz/")
public class PlayQuizController {

    @Autowired
    SessionService sessionService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserToSessionService userToSessionService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserAnswerService userAnswerService;

    @GetMapping("play/{user_id}/{quiz_id}")
    public ResponseEntity playQuiz(
            @PathVariable("user_id") long user_id,
            @PathVariable("quiz_id") long quiz_id) {
        Quiz quiz = quizService.getQuiz(quiz_id);
        Session session = sessionService.newSessionForQuiz(quiz);

        sessionService.updateSession(session);
        User user = userService.getUserById(user_id);
        userToSessionService.createNewUserToSession(user, session);

        return ResponseEntity.ok(session);
    }


    @PostMapping("start/{ses_id}")
    public ResponseEntity startSession(@PathVariable("ses_id") long ses_id){
        sessionService.setSesionStatus(ses_id,new SessionStatus(3L,"started"));
        return ResponseEntity.ok("");
    }


    @GetMapping("access_code/{ses_id}")
    public ResponseEntity getAccessCode(
            @PathVariable("ses_id") long ses_id

    ) {
        Session session = sessionService.getSessionById(ses_id);
        return ResponseEntity.ok(session.getAccessCode());

    }

    @GetMapping("join")
    public ResponseEntity getAccessCode(
            @RequestParam("user_id") Long user_id,
            @RequestParam("access_code") String accessCode
    ) {
        Session session = sessionService.getSessionByAccessCode(accessCode);
        if(session != null){
            User user = userService.getUserById(user_id);
            userToSessionService.createNewUserToSession(user, session);
            return ResponseEntity.ok(session);
        }else{
            throw new BadCredentialsException("Session already in progress or finished");
        }

    }

    @GetMapping("stats/{ses_id}")
    public ResponseEntity calculateResults(
            @PathVariable("ses_id") long ses_id
    ) {

        sessionService.setSesionStatus(ses_id,new SessionStatus(2L,"ended"));
        Map<String, Integer> response = new HashMap();

        List<UserToSession> userToSessionList = new ArrayList<>(userToSessionService.getAllBySessionId(ses_id));

        userToSessionList.forEach(
                uts -> {
                    response.put(userService.getUserById(uts.getUser_id()).getUsername(), uts.getScore());
                }
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("finish")
    public ResponseEntity finishQuiz(@RequestBody FinishedQuizDto finishedQuizDto) {
        userToSessionService.insertScore(finishedQuizDto);
        return ResponseEntity.ok("result added");
    }

}
