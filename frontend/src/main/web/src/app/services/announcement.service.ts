import { Injectable } from '@angular/core';
import {User} from "../entities/user";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Announcement} from "../entities/announcement";
import {UserService} from "./user.service";

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {

  apiURL = 'http://localhost:8080/api';
  // apiURL = '/api';
  constructor(private http: HttpClient, private userService: UserService) { }

  private handleError<T>(operation= 'operation') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }

  getAll() {
    return this.http.get<Announcement[]>(this.apiURL + `/announcement/all`,  {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('getAll'))
    );
  }

  getCreated() {
    return this.http.get<Announcement[]>(this.apiURL + `/announcement/created`,  {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('getCreated'))
    );
  }

  deleteAnnouncement(id: number) {
    return this.http.delete<Announcement>(this.apiURL + `/announcement/delete/${id}`,{
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('deleteAnnouncement'))
    );
  }
  approve(ann: Announcement) {
    return this.http.post<Announcement>(this.apiURL + '/announcement/approve', ann, {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('approve'))
    );
  }
  createAnnouncement(ann: Announcement) {
    return this.http.post<Announcement>(this.apiURL + '/announcement/create', ann, {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('createAnnouncement'))
    );
  }

  updateAnnouncement(ann: Announcement) {
    return this.http.put<Announcement>(this.apiURL + `/announcement/update`, ann, {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)
    }).pipe(
      catchError(this.handleError<any>('updateAnnouncement'))
    );
  }

}
