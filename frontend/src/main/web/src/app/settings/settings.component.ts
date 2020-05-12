import { Component, OnInit } from '@angular/core';
import {SettingsService} from "../services/settings.service";
import {UserService} from "../services/user.service";
import {Notification} from '../entities/notification';
@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  notificationSettings: Notification[];

  constructor(private settingsService: SettingsService, private userService: UserService) { }

  ngOnInit(): void {
    this.getNotifications();
  }
  checkNotification(name: string): boolean {
    if (name === 'approve') {
      return this.notificationSettings[0].seen;
    }
    if (name === 'system') {
      return this.notificationSettings[2].seen;
    }

  }
  getNotifications() {
    this.settingsService.getNotificationSettings(+this.userService.user.id).
    subscribe(res => this.notificationSettings = res);
  }
  changeSetting(catId: number, event) {
    this.settingsService.setNotificationSetting({categoryId: catId, userId: +this.userService.user.id, seen: event.target.checked}).
    subscribe();

  }
}
