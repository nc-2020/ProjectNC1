import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AchievementService} from "../services/achievement.service";
import {Quiz} from "../entities/quiz";
import {Achievement} from "../entities/achievement";
import {Category} from "../entities/category";
import {CategoryService} from "../services/category.service";

@Component({
  selector: 'app-achievement-create',
  templateUrl: './achievement-create.component.html',
  styleUrls: ['./achievement-create.component.css']
})
export class AchievementCreateComponent implements OnInit {
  title: string = 'Title for achievement';
  amountOfQuizzes: number = 0;
  categoriesList: Category[] = [];

  achievementForm = this.fb.group({
    'title': ['', [Validators.required, Validators.maxLength(50)]],
    'quantity': ['', [Validators.required, Validators.min(1), Validators.max(1000)]],
    'category': ['', [Validators.required]]
  })



  constructor(private fb: FormBuilder,
              private achievementService: AchievementService,
              private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.getCategories();
  }

  createAchievement() {
    let selectedCategory: Category[] = this.achievementForm.get('category').value;
    this.achievementService.createAchievement({
      title: this.achievementForm.get('title').value,
      amountOfQuizzes: this.achievementForm.get('quantity').value,
      categoryId: +selectedCategory[0].id
    } as Achievement).subscribe(data => {
      this.achievementForm.reset(
        {
          'title': 'Title for achievement',
          'quantity': 0
        })})
  }

  getCategories() {
    this.categoryService.getCategories().subscribe(
      (data: Category[]) => {
        this.categoriesList = data;
      }
    )
  }
}
