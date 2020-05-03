import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {Subscription} from "rxjs";
import {Question} from "../entities/question";
import {QuizService} from "../services/quiz.service";
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../services/question.service";
import {SequenceOption} from "../entities/sequence-option";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";
import {OptionService} from "../services/option.service";
import {DefaultOption} from "../entities/default-option";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit, OnDestroy {

  private routeSub: Subscription;
  quizId;
  questions: Question[] = [];
  quizAnswers: DefaultOption[] = [];


  indexQuestion = 0;
  timer = 0;
  interval: any = null;
  timeout: any = null;
  optionType = 0;
  numberOfOptions = 4;
  optionsSequence: SequenceOption[] = Array.from({length: this.numberOfOptions},() =>
    ({
      serial_num: null,
      text: ''
    }));

  constructor(private quizService: QuizService,
              private optionService: OptionService,
              private route: ActivatedRoute,
              private questionService: QuestionService) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.quizId = params['id'];
    });
    this.getQuestions();

    for (let quizA of this.quizAnswers) {
      console.log('quizA: ' + quizA);
    }
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
  }

  startQuestionTimer()  {
    this.timer = this.questions[this.indexQuestion].time;
    this.interval = setInterval(() => this.timer--, 1000);
    this.timeout = setTimeout(() => { clearInterval(this.interval);
        this.nextQuestion(false);},
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
        this.optionType = +this.questions[0].type.id;

        for (let question of this.questions) {
          this.getDefaultOptions(question);
        }
      });
  }

  getOptions(question: Question) {
    this.optionService.getOptions(question.id)
      .subscribe(options => {
      })
  }

  getDefaultOptions(question: Question) {
    console.log("from def option");
    this.optionService.getDefaultOptions(question.id)
      .subscribe((options: DefaultOption[]) => {
        // console.log(options);
        console.log(options[0]);
        this.quizAnswers.push(options[0]);
      })
  }


  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.optionsSequence, event.previousIndex, event.currentIndex);
  }
}
