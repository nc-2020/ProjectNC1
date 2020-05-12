import { Component, OnInit } from '@angular/core';
import {SettingsService} from "../services/settings.service";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  constructor(private settingsService: SettingsService, private userService: UserService) { }

  ngOnInit(): void {
  }
  approveNotificationsOn() {
    return this.settingsService.approveNotifications;
  }
  systemNotificationsOn() {
    return this.settingsService.systemNotifications;
  }
  changeSetting(catId: number, event) {
    this.settingsService.setNotificationSetting({categoryId: catId, userId: +this.userService.user.id, seen: event.target.checked}).
    subscribe();

  }
}
