import { Component, OnInit } from '@angular/core';
import {Announcement} from "../entities/announcement";
import {AnnouncementService} from "../services/announcement.service";
import {UserService} from "../services/user.service";
import {Achievement} from "../entities/achievement";
import {UserAchievement} from "../entities/user-achievement";
import {AchievementService} from "../services/achievement.service";

@Component({
  selector: 'app-achievement-dashboard',
  templateUrl: './achievement-dashboard.component.html',
  styleUrls: ['./achievement-dashboard.component.css']
})
export class AchievementDashboardComponent implements OnInit {
  achievement: Achievement;
  achievementList: Achievement[] = [];
  userAchievementList: UserAchievement[] = [];
  constructor(private achievementService: AchievementService,private userService: UserService) { }

  getRole() {
    return this.userService.user.role.name;
  }

  ngOnInit(): void {
    if (this.getRole() === 'user') {
      this.getUserAchievements();
    } else {
      this.getAchievements();
    }
  }

  getUserAchievements() {
    this.achievementService.getUserAchievements().subscribe(
      (data: UserAchievement[]) => {
        this.userAchievementList = data;
      }
    );
  }

  getAchievements() {
    this.achievementService.getAchievements().subscribe(
      (data: Achievement[]) => {
        this.achievementList = data;
      }
    );
  }
}
