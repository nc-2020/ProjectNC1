import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from "./entities/user";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AppService {
  authenticated = false;
  role = 'super admin';


  constructor(private http: HttpClient) {
  }
  private handleError<T>(operation= 'opeartion', result?: T ) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

  signUp(user: User, ): Observable<any> {
    return this.http.post<User>('api/signup', user).pipe(
      catchError(this.handleError<User>('signUp'))
    );

  }
  login(user): any {
    this.http.post<any>('api/login', user).subscribe(
      res => {}, response => {
        if (response.status === 200) {
          this.authenticated = true;
          // recieve user role (admin, user, super admin)
          // this.role = response.body.role ....
          return true;
        }
        return false;
      });
  }


}
