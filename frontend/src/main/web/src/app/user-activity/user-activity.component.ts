import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserActivity} from "../entities/user-activity";
import {UserActivityService} from "../services/user-activity.service";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-user-activity',
  templateUrl: './user-activity.component.html',
  styleUrls: ['./user-activity.component.css']
})
export class UserActivityComponent implements OnInit {

  @Input()
  userActivity: UserActivity ;
  @Output()
  onChanged = new EventEmitter<UserActivity>();
  error = '';
  message = '';


  constructor(private userActivityService: UserActivityService, private userService: UserService) { }

  ngOnInit(): void {
  }

}
