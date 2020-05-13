import {Component, Input, OnInit} from '@angular/core';
import {UserAchievement} from "../entities/user-achievement";

@Component({
  selector: 'app-achievements',
  templateUrl: './achievements.component.html',
  styleUrls: ['./achievements.component.css']
})
export class AchievementsComponent implements OnInit {
  @Input()
  userAchievement: UserAchievement;
  constructor() { }

  ngOnInit(): void {
  }

}
