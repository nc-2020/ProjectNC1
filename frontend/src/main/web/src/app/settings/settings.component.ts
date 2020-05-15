import { Component, OnInit } from '@angular/core';
import {SettingsService} from "../services/settings.service";
import {UserService} from "../services/user.service";
import {APPROVE_NOTIFICATION, SYSTEM_NOTIFICATION} from '../parameters';
@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  approveCategoryId = APPROVE_NOTIFICATION;
  systemCategoryId = SYSTEM_NOTIFICATION;
  systemNotificationsOn: boolean;
  approveNotificationsOn: boolean;
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
    console.log(this.settingsService.notificationSettings);
    this.settingsService.notificationSettings.
    forEach(not => {
      if (not.categoryId === APPROVE_NOTIFICATION) {
        this.approveNotificationsOn = not.seen;
      } else {
        if (not.categoryId === SYSTEM_NOTIFICATION) {
          this.systemNotificationsOn = not.seen;
        }
      }
    });
  }
  getUserRole() {
    return this.userService.user.role.name;
  }
}
