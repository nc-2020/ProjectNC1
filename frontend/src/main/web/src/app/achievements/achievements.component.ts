import {Component, Input, OnInit} from '@angular/core';
import {Category} from "../entities/category";

@Component({
  selector: 'app-achievements',
  templateUrl: './achievements.component.html',
  styleUrls: ['./achievements.component.css']
})
export class AchievementsComponent implements OnInit {
  @Input()
  categoryAchievement: Category;
  constructor() { }

  ngOnInit(): void {
  }

}
