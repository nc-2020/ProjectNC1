import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import {Component, OnInit, Input, OnDestroy} from '@angular/core';
import {Quiz} from "../entities/quiz";
import {Question} from "../entities/question";
import {QuestionService} from "../services/question.service";
import {Option} from "../entities/option";
import {DefaultOption} from "../entities/default-option"
import {Subscription} from "rxjs";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";
import {SequenceOption} from "../entities/sequence-option";

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
    options: null,
    type: {id: null, name: ''},
    text: '',
    quiz_id: null,
    image: ''
  };
  numberOfOptions = 4;
  options: Option[] = Array.from({length: this.numberOfOptions},()=>
    ({
      correct: false,
      text: ''
    }))
  answerTrueFalse = 'False';
  answerTypeAnswer = '';
  optionsSequence: SequenceOption[] = Array.from({length: this.numberOfOptions},()=>
    ({
      serial_num: null,
      text: ''
    }))



  isAddQuestion = false;
  selectedLevel: any;
  private routeSub: Subscription;
  defaultOption: DefaultOption = {text: ''};


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
  createQuestion(text, type) {
    let someOption;
    if (type === '1') {
      this.defaultOption.text = this.answerTrueFalse;
      someOption = this.defaultOption;
    } else if (type === '2') {
      this.defaultOption.text = this.answerTypeAnswer;
      someOption = this.defaultOption;
    } else if (type === '3') {
      someOption = this.options;
    } else if (type === '4') {
      for (let i = 0; i < this.optionsSequence.length; i++) {
        this.optionsSequence[i].serial_num = i + 1;
      }
      someOption = this.optionsSequence;
    }
    console.log("question created");
    this.questionService.createQuestion({
        options: someOption,
        text: text,
        type: {id: type},
        quiz_id: this.question.quiz_id
    } as Question)
      .subscribe(data  => {
        this.question = data;
        console.log(data);
        this.questions.push(this.question);
      });
  }

  addQuestion() {
    this.isAddQuestion = !this.isAddQuestion;
  }

  deleteQuestion(question: Question): void {
    this.questions = this.questions.filter(q => q !== question);
    this.questionService.deleteQuestion(question.id).subscribe();
  }

  isCorrectOption(option: Option) {
    option.correct = !option.correct;
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.optionsSequence, event.previousIndex, event.currentIndex);
  }
}
