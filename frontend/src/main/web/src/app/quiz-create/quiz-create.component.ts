import { Component, OnInit } from '@angular/core';
import {QuizService} from "../services/quiz.service";
import {Category} from "../entities/category";
import {Quiz} from "../entities/quiz";

@Component({
  selector: 'app-quiz-create',
  templateUrl: './quiz-create.component.html',
  styleUrls: ['./quiz-create.component.css']
})
export class QuizCreateComponent implements OnInit {

  categories: Category[];

  constructor(private quizService: QuizService) { }

  ngOnInit(): void {
    // this.categories = getCategories();
  }
  add(quiz: Quiz): void {
    this.quizService.createQuiz(quiz as Quiz)
      .subscribe(quizId => {
        quiz.id = quizId;
      });
  }


}
