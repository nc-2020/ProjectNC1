import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "./user.service";
import {Observable} from "rxjs";
import {Notification} from '../entities/notification';
import {tap} from "rxjs/operators";
@Injectable({
  providedIn: 'root'
})
export class SettingsService {

  notificationSettings: Notification[];

  private apiURL = 'http://localhost:8080/api';

  constructor(private http: HttpClient, private userService: UserService) { }

  getNotificationSettings(userId: number) {
    return  this.http.get<Notification[]>(this.apiURL + `/notification/settings/get/${userId}`,
      { headers: new HttpHeaders()
          .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
          tap(response => {this.notificationSettings = response;}))
  }
  setNotificationSetting(notification) {
    return  this.http.post<Notification>(this.apiURL + '/notification/settings', notification,
      { headers: new HttpHeaders()
          .set('Authorization',  `Bearer_${this.userService.getToken()}`)})
  }
}
