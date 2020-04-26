import { Component, OnInit } from '@angular/core';
import {QuizService} from "../services/quiz.service";
import {Category} from "../entities/category";
import {Quiz} from "../entities/quiz";
import {CategoryService} from "../services/category.service";
import {ActivatedRoute} from "@angular/router";
import { Location } from '@angular/common';

@Component({
  selector: 'app-quiz-create',
  templateUrl: './quiz-create.component.html',
  styleUrls: ['./quiz-create.component.css']
})
export class QuizCreateComponent implements OnInit {
  quiz: Quiz = {
    id: null,
    title: '',
    description: '',
    status: 'not active',
    userId: ''
  };
  isIdGet = false;
  receivedQuiz: Quiz;
  categories: Category[] = [];

  constructor(
    private location: Location,
    private route: ActivatedRoute,
    private quizService: QuizService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    // this.getData();
  }

  goBack(): void {
    this.location.back();
  }
  createQuiz(quiz: Quiz) {
    console.log("quiz created");
    this.quizService.createQuiz(quiz)
      .subscribe(data  => {
        console.log(data);
        this.quiz = data;
      });
  }

  // getCategories(): void {
  //   this.categoryService.getCategories()
  //     .subscribe((categories: Category) =>
  //       this.categories = categories);
  // }
}
