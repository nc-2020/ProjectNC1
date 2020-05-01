import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {Question} from "../entities/question";
import {QuizService} from "../services/quiz.service";
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../services/question.service";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit, OnDestroy {

  private routeSub: Subscription;
  quizId;
  questions: Question[] = [];

  constructor(quizService: QuizService,
              private route: ActivatedRoute,
              private questionService: QuestionService,) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.quizId = params['id'];
    });
    this.getQuestions();
  }
  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }

  getQuestions() {
    this.questionService.getQuestions(this.quizId)
      .subscribe(questions => {
        console.log(questions);
        this.questions = questions;
      });
  }
}
