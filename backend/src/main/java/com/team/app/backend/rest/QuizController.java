package com.team.app.backend.rest;

import com.team.app.backend.dto.QuizAddDto;
import com.team.app.backend.dto.QuestionDefAddDto;
import com.team.app.backend.dto.QuestionOptAddDto;
import com.team.app.backend.dto.QuestionSeqAddDto;
import com.team.app.backend.persistance.model.Question;
import com.team.app.backend.persistance.model.Quiz;
import com.team.app.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("api")
public class QuizController {

    @Autowired
    QuizService quizService;


    @PostMapping("/quiz")
    public Long createMewQuiz(
            @RequestBody QuizAddDto quizDto) {
        System.out.println(quizDto.getUser_id());
        return quizService.addQuiz(quizDto);
    }

    @DeleteMapping("/quiz/{id}")
    public void deleteQuiz(@PathVariable("id") long id) {
        quizService.deleteQuiz(id);
    }

    @GetMapping("/question/{id}")
    public Question getQuestion(@PathVariable("id") long id) {
        return quizService.getQuestion(id);
    }


 //   @PostMapping("/question/1")
    @RequestMapping(value={ "/question/1", "/question/2" },method = { RequestMethod.POST })
    public void createNewQuestion(@RequestBody QuestionDefAddDto questionDefAddDto) {
        System.out.println("add question1");
        quizService.addDefQuestion(questionDefAddDto);
    }

    @PostMapping("/question/3")
    public void createNewQuestion(@RequestBody QuestionOptAddDto questionOptAddDto) {
        System.out.println("add question3");
        quizService.addOptQuestion(questionOptAddDto);
    }

    @PostMapping("/question/4")
    public void createNewQuestion(@RequestBody QuestionSeqAddDto questionSeqAddDto) {
        System.out.println("add question4");
        quizService.addSeqOptQuestion(questionSeqAddDto);
    }

    @DeleteMapping("/question/{id}")
    public void deleteQuestion(@PathVariable("id") long id) {
        quizService.deleteQuestion(id);
    }


    @GetMapping("/quiz/quest/{id}")
    public List<Question> questions(@PathVariable("id") long id) {
        return quizService.getQuizQuestion(id);
    }

    @GetMapping("/quiz/{id}")
    public Quiz quiz(@PathVariable("id") long id) {
        //System.out.println(id);
        return quizService.getQuiz(id);
    }

    @GetMapping("/quiz")
    public List<Quiz> quizes() {
        return quizService.getAllQuizes();
    }

    @GetMapping("/user/quiz/{id}")
    public List<Quiz> userQuizes(@PathVariable("id") long id) {
        return quizService.getUserQuizes(id);
    }


}
