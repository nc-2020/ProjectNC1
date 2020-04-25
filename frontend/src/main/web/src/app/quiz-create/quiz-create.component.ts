import { Component, OnInit } from '@angular/core';
import {QuizService} from "../services/quiz.service";
import {Category} from "../entities/category";
import {Quiz} from "../entities/quiz";
import {CategoryService} from "../services/category.service";

@Component({
  selector: 'app-quiz-create',
  templateUrl: './quiz-create.component.html',
  styleUrls: ['./quiz-create.component.css']
})
export class QuizCreateComponent implements OnInit {
  quiz: Quiz = {
    id: '',
    title: '',
    description: '',
    userId: ''
  };
  receivedQuiz: Quiz;
  categories: Category[] = [];

  constructor(private quizService: QuizService, private categoryService: CategoryService) { }

  ngOnInit(): void {
    // this.getData();
  }

  createQuiz(quiz: Quiz) {
    this.quizService.createQuiz(quiz)
      .subscribe(data  => {
        this.quiz.id = data;
      });
  }

  // getCategories(): void {
  //   this.categoryService.getCategories()
  //     .subscribe((categories: Category) =>
  //       this.categories = categories);
  // }

  // getData() {
  //   this.categoryService.getData().subscribe((data:Category) => {
  //       console.log(data);
  //       this.categories = data;
  //     }
  //   )
  // }
}
