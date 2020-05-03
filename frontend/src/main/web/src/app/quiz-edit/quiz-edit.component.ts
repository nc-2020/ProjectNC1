import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Component, OnDestroy, OnInit} from '@angular/core';
import {Question} from "../entities/question";
import {QuestionService} from "../services/question.service";
import {Option} from "../entities/option";
import {DefaultOption} from "../entities/default-option"
import {Subscription} from "rxjs";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";
import {SequenceOption} from "../entities/sequence-option";
import {FormBuilder, Validators} from "@angular/forms";

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
    'questionType': ['not selected', Validators.required],
    'questionTime': ['not selected', Validators.required]
  })
  questions: Question[] = [];
  titleEditor = 'Add a question';

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

  showEdit() {
    this.titleEditor = 'Edit a question';
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
    this.questionForm.reset({
      'questionType': 'not selected',
      'questionTime': 'not selected'}
    );
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
