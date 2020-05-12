import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "./user.service";
import {Observable} from "rxjs";
import {Notification} from '../entities/notification';
@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  approveNotifications = true;
  systemNotifications = true;
  apiURL = 'http://localhost:8080/api';
  constructor(private http: HttpClient, private userService: UserService) { }

  setNotificationSetting(notification) {
    if(notification.categoryId === 1) {
      this.approveNotifications = notification.seen ? true : false;
    }
    if(notification.categoryId === 2) {
      this.systemNotifications = notification.seen ? true : false;
    }
    return  this.http.post<Notification>(this.apiURL + '/notification/settings', notification,
      { headers: new HttpHeaders()
          .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
  }
}
