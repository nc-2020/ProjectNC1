import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "./user.service";
import {Observable} from "rxjs";
import {Notification} from '../entities/notification';
@Injectable({
  providedIn: 'root'
})
export class SettingsService {

    notificationSettings: Notification[];

  private apiURL = 'http://localhost:8080/api';
  // private apiURL = '/api';
  constructor(private http: HttpClient, private userService: UserService) {

  }

  getNotificationSettings(userId: number) {
    return  this.http.get<Notification[]>(this.apiURL + `/notification/settings/get/${userId}`,
      { headers: new HttpHeaders()
          .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
  }
  setNotificationSetting(notification) {
    return  this.http.post<Notification>(this.apiURL + '/notification/settings', notification,
      { headers: new HttpHeaders()
          .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
  }
}
