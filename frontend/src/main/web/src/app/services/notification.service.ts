import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "./user.service";
import {Observable, of, throwError} from "rxjs";
import {Announcement} from "../entities/announcement";
import {catchError, tap} from "rxjs/operators";
import {Notification} from '../entities/notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  // private apiUrl = 'http://localhost:8080/api/notification';
  private apiUrl = '/api/notification'

  constructor(private http: HttpClient, private userService: UserService) { }
  notifications: Notification[] = [];

  getAll(userId: number) {
    return this.http.get<Notification[]>(this.apiUrl + `/get/${userId}`,  {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe( tap(res => this.notifications = res),
      catchError(this.handleError<any>('getAll'))
    );
  }

  delete(notification: Notification[]) {
    if (notification.length > 0) {
      console.log('Looooolll')
      const options = {
        headers: new HttpHeaders()
          .set('Authorization', `Bearer_${this.userService.getToken()}`),
        body: notification
      }
      return this.http.delete<Notification>(this.apiUrl + `/delete`, options).pipe(
        catchError(this.handleError<any>('delete'))
      );
    } else return of([]);
  }

  private handleError<T>(operation= 'operation') {
    return (error: any): Observable<T> => {
      return throwError(error);
    };
  }
}
