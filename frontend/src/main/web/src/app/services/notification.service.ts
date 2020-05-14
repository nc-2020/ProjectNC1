import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "./user.service";
import {Observable, throwError} from "rxjs";
import {Announcement} from "../entities/announcement";
import {catchError} from "rxjs/operators";
import {Notification} from '../entities/notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private apiUrl = 'http://localhost:8080/api/notification';
  // private apiUrl = '/api/notification';
  constructor(private http: HttpClient, private userService: UserService) { }

  private handleError<T>(operation= 'opeartion') {
    return (error: any): Observable<T> => {
      return throwError(error);
    };
  }

  getAll(userId: number) {
    return this.http.get<Notification[]>(this.apiUrl + `/get/${userId}`,  {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('getAll'))
    );
  }

  delete(notification: Notification[]) {
    const options = {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`),
      body: notification
    }
    return this.http.delete<Notification>(this.apiUrl + `/delete`, options).pipe(
      catchError(this.handleError<any>('delete'))
    );
  }

}
