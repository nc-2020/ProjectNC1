import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {Subscription} from "rxjs";
import {Question} from "../entities/question";
import {QuizService} from "../services/quiz.service";
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../services/question.service";
import {SequenceOption} from "../entities/sequence-option";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";

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
  optionType = 0;
  numberOfOptions = 4;
  optionsSequence: SequenceOption[] = Array.from({length: this.numberOfOptions},()=>
    ({
      serial_num: null,
      text: ''
    }))

  constructor(quizService: QuizService,
              private route: ActivatedRoute,
              private questionService: QuestionService,) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.quizId = params['id'];
    });
    this.getQuestions();

  }

  nextQuestion(): void {
    if (this.indexQuestion < this.questions.length) {
      this.indexQuestion++;
    }
    this.optionSwitcher();
  }

  optionSwitcher() {
    console.log(this.questions[this.indexQuestion].type.id);

    switch (+this.questions[this.indexQuestion].type.id) {
      case 1:
        this.optionType = 1;
        break;
      case 2:
        this.optionType = 2;
        break;
      case 3:
        this.optionType = 3;
        break;
      case 4:
        this.optionType = 4;
        break;
      default:
        this.optionType = 0;
    }
    console.log(this.optionType);
  }


  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }

  getQuestions() {
    this.questionService.getQuestions(this.quizId)
      .subscribe(questions => {
        console.log(questions);
        this.questions = questions;
        this.optionType = +this.questions[0].type.id;
      });
  }

  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.optionsSequence, event.previousIndex, event.currentIndex);
  }
}
