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
import {Option} from "../entities/option";
import {Answer} from "../entities/answer";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit, OnDestroy {

  private routeSub: Subscription;
  quizId;
  questions: Question[] = [];
  userAnswers: Answer[] = [];
  optionalAnswers: Option[] = [];
  questionOptions = new Map();
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
      console.log(this.getScore());
    }
    this.optionSwitcher();
  }

  optionSwitcher() {
    switch (+this.questions[this.indexQuestion].type.id) {
      case 1:
        this.optionType = 1;
        break;
      case 2:
        this.optionType = 2;
        break;
      case 3: {
        this.optionType = 3;
        this.optionalAnswers = this.questionOptions.get(this.questions[this.indexQuestion].id) as Option[];
        break;
      }
      case 4:
        this.optionType = 4;
        this.optionsSequence = this.questionOptions.get(this.questions[this.indexQuestion].id);
        break;
      default:
        this.optionType = 0;
    }
  }

  startQuestionTimer()  {
    this.optionSwitcher();
    this.timer = this.questions[this.indexQuestion].time;
    this.interval = setInterval(() => this.timer--, 1000);
    this.timeout = setTimeout(() => { clearInterval(this.interval);
        this.nextQuestion(false);},
      (this.questions[this.indexQuestion].time + 1) * 1000);
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }
  defAnswer(answer: string) {
    this.userAnswers.push({questionId: this.questions[this.indexQuestion].id ,
      points: this.questionOptions.get(this.questions[this.indexQuestion].id)[0].answer === answer ? 1 : 0});
    this.nextQuestion(true);
  }

  seqAnswer() {
    let questionPoints = 0;

    for (let i = 0; i < this.optionsSequence.length; i++) {
      if (i + 1 === this.optionsSequence[i].serial_num) {
        questionPoints += 0.25;
      }
    }
    console.log(questionPoints);
    this.userAnswers.push({questionId: this.questions[this.indexQuestion].id,
       points: questionPoints
    })

    this.nextQuestion(true);
  }

  getQuestions() {
    this.questionService.getQuestions(this.quizId)
      .subscribe(questions => {
        this.questions = questions;
        this.optionType = +this.questions[0].type.id;

        for (let question of this.questions) {
          this.getOptions(question);
        }
      });
  }

  getOptions(question: Question) {
    if(question.type.name === 'a,b,c,d') {
      this.optionService.getOptions(question.id)
        .subscribe(options =>
        {this.questionOptions.set(question.id, options)
        }, error => console.error(error.message));
    }
    if(question.type.name === 'sequence') {
      this.optionService.getSequenceOptions(question.id)
        .subscribe(options =>
        {this.questionOptions.set(question.id, options)});
    }
    if(question.type.name === 'enter' || question.type.name === 'true/false') {
        this.optionService.getDefaultOptions(question.id)
          .subscribe(options =>
          {this.questionOptions.set(question.id, options)});
    }
  }



getScore(): number {
    let score = 0;
    this.userAnswers.forEach(x => score += x.points);
    return score;
}
  drop(event: CdkDragDrop<string[]>) {
    moveItemInArray(this.optionsSequence, event.previousIndex, event.currentIndex);
  }
}
