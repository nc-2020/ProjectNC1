import { Injectable } from '@angular/core';
import {User} from "./entities/user";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Announcement} from "./entities/announcement";

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {


  constructor(private http: HttpClient) { }

  private handleError<T>(operation= 'opeartion') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }
  deleteAnnouncement(ann: Announcement) {
    return this.http.delete<Announcement>(`api/announcement/delete/${ann.id}`).pipe(
      catchError(this.handleError<any>('deleteAnnouncement'))
    );
  }
}
