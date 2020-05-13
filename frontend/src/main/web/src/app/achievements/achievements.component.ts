import {Component, Input, OnInit} from '@angular/core';
import {UserAchievement} from "../entities/user-achievement";
import {formatPercent} from "@angular/common";

@Component({
  selector: 'app-achievements',
  templateUrl: './achievements.component.html',
  styleUrls: ['./achievements.component.css']
})
export class AchievementsComponent implements OnInit {
  @Input()
  userAchievement: UserAchievement;
  percentStatus;

  constructor() { }

  ngOnInit(): void {
    this.percentStatus = (this.userAchievement.played / this.userAchievement.quizAmount * 100);
  }

  boxStatus(): string {
    let achievementBoxStatus = 'achievement-box-status-';
    if (this.percentStatus <= 25) {
      achievementBoxStatus += '25';
    } else if (this.percentStatus <= 50) {
      achievementBoxStatus += '50';
    } else if (this.percentStatus <= 75) {
      achievementBoxStatus += '75';
    } else if (this.percentStatus <= 100) {
      achievementBoxStatus += '100';
    }
    return achievementBoxStatus;
  }
}
