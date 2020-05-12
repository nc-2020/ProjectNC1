import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {Question} from "../entities/question";
import {QuizService} from "../services/quiz.service";
import {ActivatedRoute} from "@angular/router";
import {QuestionService} from "../services/question.service";
import {SequenceOption} from "../entities/sequence-option";
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";
import {OptionService} from "../services/option.service";
import {Option} from "../entities/option";
import {Answer} from "../entities/answer";
import {UserService} from "../services/user.service";
import {UserSessionResult} from "../entities/UserSessionResult";

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit, OnDestroy {
  private routeSub: Subscription;
  questionOptionPoints = 0;
  quizId;
  sessionId;
  quizScore = 0;
  questions: Question[] = [];
  userAnswers: Answer[] = [];
  optionalAnswers: Option[] = [];
  questionOptions = new Map();
  indexQuestion: number = 0;
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
              private questionService: QuestionService,
              private userService: UserService) { }

  ngOnInit(): void {
    this.routeSub = this.route.params.subscribe(params => {
      this.quizId = params['id'];
      this.sessionId = params['sessionId'];
    });
    this.getQuestions();

  }

  nextQuestion(clear: boolean): void {
    if(clear){
      clearInterval(this.interval);
      clearTimeout(+this.timeout);
    }
    if (this.indexQuestion < this.questions.length - 1) {
      this.indexQuestion++;
      this.startQuestionTimer();
    } else {
      this.interval = null;
    }
    this.optionSwitcher();
    if (this.indexQuestion === this.questions.length - 1) {
      console.log('finished!')
        this.finishSession();
    }
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
        this.userAnswers.push({questionId: this.questions[this.indexQuestion].id , points: 0});
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

  addPoint(point: number, event?) {

    const coef = 1 / this.optionalAnswers.filter(x => x.is_correct).length;
    point *= coef;
    this.questionOptionPoints += event.target.checked ? point : -point;
  }


  sendOptionAnswer() {
    this.userAnswers.push({questionId: this.questions[this.indexQuestion].id,
      points: this.questionOptionPoints
    })
    this.nextQuestion(true);
  }


  seqAnswer() {
    let questionPoints = 0;

    for (let i = 0; i < this.optionsSequence.length; i++) {
      if (i + 1 === this.optionsSequence[i].serial_num) {
        questionPoints += 0.25;
      }
    }
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


  drop(event: CdkDragDrop<string[]>) {moveItemInArray(this.optionsSequence, event.previousIndex, event.currentIndex);}

  getUserRole() {
    return this.userService.user.role.name;
  }

  startNewGame() {
    this.quizService.startSession(this.sessionId).subscribe(data =>
      console.log(data))
  }

  finishSession() {
    this.quizService.sendSessionStats({
      ses_id : this.sessionId,
      user_id : +this.userService.user.id,
      score : this.getScore()
    } as UserSessionResult).subscribe(data => {
      console.log(data);
    })
  }

}
