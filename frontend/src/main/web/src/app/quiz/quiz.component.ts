import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
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
  indexQuestion = 0;
  timer = 0;
  interval: any = null;
  timeout: any = null;
  constructor(private quizService: QuizService,
              private route: ActivatedRoute,
              private questionService: QuestionService,) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.quizId = params['id'];
    });
    this.getQuestions();

  }

  nextQuestion(clear: boolean): void {
    if(clear){
      clearInterval(this.interval);
      clearTimeout(this.timeout);
    }
    if (this.indexQuestion < this.questions.length) {
      this.indexQuestion++;
      this.startQuestionTimer();
    }
    else {
      this.timer = 0;
    }
  }

startQuestionTimer()  {
  this.timer = this.questions[this.indexQuestion].time;
  this.interval = setInterval(() => this.timer--, 1000);
  this.timeout = setTimeout(() => { clearInterval(this.interval); this.nextQuestion(false); this.indexQuestion++},
        (this.questions[this.indexQuestion].time + 1) * 1000);
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
