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



  constructor(private settingsService: SettingsService, private userService: UserService) { }

  ngOnInit(): void {
  this.getNotificationSettings();
  }
  checkNotification(name: string): boolean {
    if (name === 'approve' && this.settingsService.notificationSettings.length >= 0) {
      return this.settingsService.notificationSettings[0].seen;
    }
    if (name === 'system' && this.settingsService.notificationSettings.length >= 2) {
      return this.settingsService.notificationSettings[2].seen;
    }
    return true;

  }
  getNotificationSettings() {
    this.settingsService.getNotificationSettings(+this.userService.user.id).
    subscribe(res => this.settingsService.notificationSettings = res);
  }

  changeSetting(catId: number, event) {
    this.settingsService.setNotificationSetting({categoryId: catId, userId: +this.userService.user.id, seen: event.target.checked}).
    subscribe();

  }
}
