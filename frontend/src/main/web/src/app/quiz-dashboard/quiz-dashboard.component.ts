import { Component, OnInit } from '@angular/core';
import { Quiz } from '../entities/quiz';
import { QuizService } from '../services/quiz.service';

@Component({
  selector: 'app-quiz-dashboard',
  templateUrl: './quiz-dashboard.component.html',
  styleUrls: ['./quiz-dashboard.component.css']
})
export class QuizDashboardComponent implements OnInit {
  quizzes: Quiz[] = [];

  constructor(private quizService: QuizService) { }

  ngOnInit(): void {
    this.getQuizzes();
  }

  getQuizzes(): void {
    this.quizService.getQuizzes()
      .subscribe(quizzes => this.quizzes = quizzes);
  }

  deleteQuiz(quiz: Quiz) {
    this.quizzes = this.quizzes.filter(q => q !== quiz);
    this.quizService.deleteQuiz(quiz);
  }
}
