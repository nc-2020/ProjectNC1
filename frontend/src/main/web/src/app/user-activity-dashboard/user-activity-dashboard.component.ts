import { Component, OnInit } from '@angular/core';
import {UserActivityService} from "../services/user-activity.service";
import {UserService} from "../services/user.service";
import {UserActivity} from "../entities/user-activity";

@Component({
  selector: 'app-user-activity-dashboard',
  templateUrl: './user-activity-dashboard.component.html',
  styleUrls: ['./user-activity-dashboard.component.css']
})
export class UserActivityDashboardComponent implements OnInit {

  userActivity: UserActivity;
  userActivitys: UserActivity[] = [];

  editOnly = false;
  constructor(private userActivityService: UserActivityService, private userService: UserService) { }

  getRole() {
    return this.userService.user.role.name;
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.userActivityService.getAll().
    subscribe(res => {this.userActivitys = res;console.table(res)},
      error => this.userActivitys = [] );
  }


}
