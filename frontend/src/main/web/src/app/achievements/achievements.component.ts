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
  linkToIconAchievement;
  urlIcon;
  percentStatus;

  constructor() { }

  ngOnInit(): void {
    this.percentStatus = (this.userAchievement.played / this.userAchievement.quizAmount * 100);
    this.getIcon()
    this.urlIcon = 'url("' + this.linkToIconAchievement +'")';
  }

  getIcon() {
    if (this.userAchievement.title === 'Geography mind') {
      this.linkToIconAchievement = 'https://cdn3.iconfinder.com/data/icons/project-management-49/50/34-128.png';
    } else if (this.userAchievement.title === 'Programming mind') {
      this.linkToIconAchievement = 'https://cdn3.iconfinder.com/data/icons/project-management-49/50/20-128.png';
    } else if (this.userAchievement.title === 'Math mind') {
      this.linkToIconAchievement = 'https://cdn3.iconfinder.com/data/icons/project-management-49/50/26-128.png';
    } else if (this.userAchievement.title === 'History mind') {
      this.linkToIconAchievement = 'https://cdn3.iconfinder.com/data/icons/project-management-49/50/69-128.png';
    } else if (this.userAchievement.title === 'Modern mind') {
      this.linkToIconAchievement = 'https://cdn3.iconfinder.com/data/icons/project-management-49/50/48-128.png';
    } else {
      this.linkToIconAchievement = 'https://cdn3.iconfinder.com/data/icons/project-management-49/50/61-128.png';
    }
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
