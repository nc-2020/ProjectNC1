import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserActivity} from "../entities/user-activity";
import {UserActivityService} from "../services/user-activity.service";
import {UserService} from "../services/user.service";
import {QUIZ_PLAY_ACTIVITY,
  QUIZ_CREATE_ACTIVITY,
  ACHIEVEMENT_RECEIVE_ACTIVITY,
  ANNOUNCEMENT_CREATE_ACTIVITY ,
  QUIZ_LIKE_ACTIVITY} from '../parameters';


@Component({
  selector: 'app-user-activity',
  templateUrl: './user-activity.component.html',
  styleUrls: ['./user-activity.component.css']
})
export class UserActivityComponent implements OnInit {
  quizPlayCatId=QUIZ_PLAY_ACTIVITY;
  quizCreateCatId=QUIZ_CREATE_ACTIVITY;
  achRecCatId=ACHIEVEMENT_RECEIVE_ACTIVITY;
  annCreateCatId=ANNOUNCEMENT_CREATE_ACTIVITY;
  quizLikeCatId=QUIZ_LIKE_ACTIVITY;

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
