import { Component, OnInit } from '@angular/core';
import {SettingsService} from "../services/settings.service";
import {UserService} from "../services/user.service";
import {ANNOUNCEMENT_NOTIFICATION, SYSTEM_NOTIFICATION, QUIZ_NOTIFICATION} from '../parameters';
@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {


  announcementCategoryId = ANNOUNCEMENT_NOTIFICATION;
  quizCategoryId = QUIZ_NOTIFICATION;
  systemCategoryId = SYSTEM_NOTIFICATION;
  systemNotificationsOn: boolean;
  announcementNotificationsOn: boolean;
  quizNotificationsOn: boolean;
  constructor(private settingsService: SettingsService, private userService: UserService) { }

  ngOnInit(): void {
    this.getNotificationSettings();
  }

  getNotificationSettings() {
    this.settingsService.getNotificationSettings(+this.userService.user.id).subscribe(
      res => this.checkNotification());
  }

  changeSetting(catId: number, event) {
    this.settingsService.setNotificationSetting(
      {categoryId: catId, userId: +this.userService.user.id, seen: event.target.checked}).
    subscribe();

  }
  checkNotification() {
    this.settingsService.notificationSettings.
    forEach(not => {
      if (not.categoryId === ANNOUNCEMENT_NOTIFICATION) {
        this.announcementNotificationsOn = not.seen;
      } else {
        if (not.categoryId === SYSTEM_NOTIFICATION) {
          this.systemNotificationsOn = not.seen;
        } else {
          if (not.categoryId === QUIZ_NOTIFICATION) {
            this.quizNotificationsOn = not.seen;
          }
        }
      }
    });
  }
  getUserRole() {
    return this.userService.user.role.name;
  }
}
