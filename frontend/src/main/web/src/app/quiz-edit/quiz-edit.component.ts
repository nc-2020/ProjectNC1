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
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-quiz-edit',
  templateUrl: './quiz-edit.component.html',
  styleUrls: ['./quiz-edit.component.css']
})
export class QuizEditComponent implements OnInit, OnDestroy {
  quiz_id;
  // quizEditForm: FormGroup = new FormGroup({
  //   'questionText': new FormControl(null, Validators.required),
  //   'questionType': new FormControl('1', Validators.required),
  // })

  questionForm = this.fb.group({
    'questionText': ['', Validators.required],
    'questionType': ['', Validators.required],
    'questionTime': ['', Validators.required]
  })
  questions: Question[] = [];
  titleEditor: any;

  numberOfOptions = 4;
  options: Option[] = Array.from({length: this.numberOfOptions},()=>
    ({
      is_correct: false,
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
  private routeSub: Subscription;
  defaultOption: DefaultOption = {answer: ''};


  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private questionService: QuestionService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.quiz_id = params['id'];
    });
    this.getQuestions();
  }
  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }
  showAdd() {
    this.titleEditor = 'Add a question';
    this.isAddQuestion = !this.isAddQuestion;
  }
  showEdit() {
    this.titleEditor = 'Edit a question';
    this.isAddQuestion = !this.isAddQuestion;
  }




  //CRUD
  getQuestions(): void {
    this.questionService.getQuestions(this.quiz_id)
      .subscribe(questions => {
        console.log(questions);
        this.questions = questions;
      }
    );
  }

  getQuestion(question_id): void {
    this.questionService.getQuestion(question_id)
      .subscribe(question => {
        console.log(question);
        // this.questions.push(question);
      })
  }

  determineTypeQuestion(): any[] {
    let someOption=[];
    switch (this.questionForm.get('questionType').value) {
      case '1':
        this.defaultOption.answer = this.answerTrueFalse;
        someOption.push(this.defaultOption);
        break;
      case '2':
        this.defaultOption.answer = this.answerTypeAnswer;
        someOption.push(this.defaultOption);
        break;
      case '3':
        someOption = this.options;
        break;
      case '4':
        for (let i = 0; i < this.optionsSequence.length; i++) {
          this.optionsSequence[i].serial_num = i + 1;
        }
        someOption = this.optionsSequence;
        break;
    }

    return someOption;
  }


  createQuestion() {
    let someOption = this.determineTypeQuestion();
    console.log("question created");
    let question: Question  = {
      id: null,
      time: this.questionForm.get('questionTime').value,
      options: someOption,
      type: {id: this.questionForm.get('questionType').value, name: ''},
      text: this.questionForm.get('questionText').value,
      max_points: null,
      quiz_id: this.quiz_id,
      image: ''
    };



    this.questionService.createQuestion(question as Question)
      .subscribe(data  => {
        question.id = data.id;
        this.questions.push(question);
        //this.getQuestion(data.id);
      });
  }

  deleteQuestion(question: Question): void {
    this.questions = this.questions.filter(q => q !== question);
    this.questionService.deleteQuestion(question.id).subscribe();
  }

  isCorrectOption(option: Option) {
    option.is_correct = !option.is_correct;
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.optionsSequence, event.previousIndex, event.currentIndex);
  }
}
