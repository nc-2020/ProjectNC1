import {Component, EventEmitter, OnInit} from '@angular/core';
import { Quiz } from '../entities/quiz';
import { QuizCardComponent } from '../quiz-card/quiz-card.component';
import { QuizService } from '../services/quiz.service';
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-quiz-dashboard',
  templateUrl: './quiz-dashboard.component.html',
  styleUrls: ['./quiz-dashboard.component.css']
})
export class QuizDashboardComponent implements OnInit {
  quizzes: Quiz[] = [];
  userQuizzes: Quiz[] = [];
  currentTab = 'Quizzes';

  constructor(private quizService: QuizService, private userService: UserService) { }

  ngOnInit(): void {
    if(this.getRole() === 'user') {
      this.getQuizzes();
      this.getUserQuizzes();
    } else {
      this.getCreatedQuizzes();
    }

  }
  getRole() {
    return this.userService.user.role.name;
  }
  getCreatedQuizzes() {
    this.quizService.getCreatedQuizzes().
    subscribe(quizzes => this.quizzes = quizzes);
  }
  getQuizzes(): void {
    this.quizService.getQuizzes()
      .subscribe(quizzes => this.quizzes = quizzes);
  }

  getUserQuizzes(): void {
    this.quizService.getUserQuizzes()
      .subscribe(quizzes => this.userQuizzes = quizzes);
  }

  deleteQuiz(quiz: Quiz) {
    this.quizzes = this.quizzes.filter(q => q !== quiz);
    this.quizService.deleteQuiz(quiz);
  }

  quizTab(tab: string) {
      this.currentTab = tab;
  }

  public toggleSelected(quiz: Quiz) {
    quiz.favourite = !quiz.favourite;
  }
}
