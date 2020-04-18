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
  role = 'user';

  constructor(private http: HttpClient) {
  }
  private handleError<T>(operation= 'opeartion', result?: T ) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

  signUp(user: User): Observable<any> {
    return this.http.post<User>('api/signup', user).pipe(
      catchError(this.handleError<User>('signUp'))
    );

  }
  login(user): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/login', user);
  }


}
