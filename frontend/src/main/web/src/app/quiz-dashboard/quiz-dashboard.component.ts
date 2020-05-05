import {Component, EventEmitter, OnInit} from '@angular/core';
import { Quiz } from '../entities/quiz';
import { QuizCardComponent } from '../quiz-card/quiz-card.component';
import { QuizService } from '../services/quiz.service';

@Component({
  selector: 'app-quiz-dashboard',
  templateUrl: './quiz-dashboard.component.html',
  styleUrls: ['./quiz-dashboard.component.css']
})
export class QuizDashboardComponent implements OnInit {
  quizzes: Quiz[] = [];
  userQuizzes: Quiz[] = [];
  currentTab = 'Quizzes';

  constructor(private quizService: QuizService) { }

  ngOnInit(): void {
    this.getQuizzes();
    this.getUserQuizzes();
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
