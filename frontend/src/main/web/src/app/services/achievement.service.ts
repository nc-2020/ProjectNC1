import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserService} from "./user.service";
import {Category} from "../entities/category";
import {catchError} from "rxjs/operators";
import {Observable, throwError} from "rxjs";
import {Achievement} from "../entities/achievement";
import {UserAchievement} from "../entities/user-achievement";

@Injectable({
  providedIn: 'root'
})
export class AchievementService {

  private achievementUrl = 'http://localhost:8080/api/achievement';
  // private achievementUrl = '/api/achievement';

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getAchievements() {
    return this.http.get<UserAchievement>(this.achievementUrl + `/${this.userService.user.id}`,{ headers: new HttpHeaders()
        .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
      catchError(this.handleError<any>('getAchievements'))
    );
  }

  // getAchievements(): Observable<Achievement[]> {
  //   return this.http.get<Achievement[]>(this.achievementUrl + '/achievements', { headers: new HttpHeaders()
  //       .set('Authorization',  `Bearer_${this.userService.getToken()}`)}).pipe(
  //     catchError(this.handleError<any>('getAchievements'))
  //   );
  // }

  private handleError<T>(operation= 'operation') {
    return (error: any): Observable<T> => {
      console.log(operation + ' ' + error);
      return throwError(error);
    };
  }
}
