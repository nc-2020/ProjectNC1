import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {Component, OnInit, Input, OnDestroy} from '@angular/core';
import {Quiz} from "../entities/quiz";
import {Question} from "../entities/question";
import {QuestionService} from "../services/question.service";
import {Option} from "../entities/option";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-quiz-edit',
  templateUrl: './quiz-edit.component.html',
  styleUrls: ['./quiz-edit.component.css']
})
export class QuizEditComponent implements OnInit, OnDestroy {
  questions: Question[] = [];
  question: Question = {
    id: null,
    time: null,
    options: [],
    type: {id: null, name: ''},
    text: '',
    quiz_id: null,
    image: ''
  };

  isAddQuestion = false;

  private routeSub: Subscription;
  constructor(
    private route: ActivatedRoute,
    private questionService: QuestionService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      // console.log(params); //log the entire params object
      // console.log(params['id']); //log the value of id
      this.question.quiz_id = params['id'];
      this.getQuestions();
    });
  }
  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }
  goBack(): void {
    this.location.back();
  }
  getQuestions(): void {
    this.questionService.getQuestions()
      .subscribe(questions => this.questions = questions);
  }
  createQuestion(question: Question) {
    console.log("question created");
    this.questionService.createQuestion(question)
      .subscribe(data  => {
        console.log(data);
        this.question = data;
        this.questions.push(question);
      });
  }

  addQuestion() {
    this.isAddQuestion = !this.isAddQuestion;
  }

  deleteQuestion(question: Question): void {
    this.questions = this.questions.filter(q => q !== question);
    this.questionService.deleteQuestion(question).subscribe();
  }
}
