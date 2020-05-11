package com.team.app.backend.rest;

import com.team.app.backend.dto.FinishedQuizDto;
import com.team.app.backend.dto.UserAnswerDto;
import com.team.app.backend.persistance.model.*;
import com.team.app.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
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
        //String accessCode = AccessCodeProvider.createAccessCode(session, quiz);
        session.setAccess_code(session.getId().toString());
        sessionService.updateSession(session);
        User user = userService.getUserById(user_id);

        UserToSession userToSession = userToSessionService.createNewUserToSession(user, session);

        return ResponseEntity.ok(session);
    }

    @GetMapping("join")
    public ResponseEntity joinQuiz(
            @RequestParam("user_id") Long user_id,
            @RequestParam("access_code") Long accessCode
    ) {
        //Quiz quiz = quizService.getQuiz(
                //AccessCodeProvider.parseAccessCode(accessCode).get("quiz_id"));
        //sessionService.getSessionById(
         //       AccessCodeProvider.parseAccessCode(accessCode).get("session_id") Session session = );
        Session session = sessionService.getSessionById(accessCode);
        User user = userService.getUserById(user_id);
        UserToSession userToSession = userToSessionService.createNewUserToSession(user, session);
        return ResponseEntity.ok("");
    }

    @PostMapping("stats")
    public ResponseEntity calculateResults(
            @RequestParam("quiz_id") Long quizId
    ) {
        Map<String, Integer> response = new HashMap();
        List<UserToSession> userToSessionList = new ArrayList<>();
        List<Session> sessionList = sessionService.getAllByQuizId(quizId);
        sessionList.forEach(
                s -> {
                    userToSessionList.addAll(userToSessionService.getAllBySessionId(s.getId()));
                }
        );
        userToSessionList.forEach(
                uts -> {
                    response.put(userService.getUserNameById(uts.getUser_id()), uts.getScore());
                }
        );
        return ResponseEntity.ok(response);
    }

    @RequestMapping("finish")
    public ResponseEntity finishQuiz(@RequestBody FinishedQuizDto finishedQuizDto) {
        // TODO: complete
        return null;
    }

}
