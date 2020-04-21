import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from "./entities/user";
import {Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  authenticated = false;
  role = 'super admin';

  constructor(private http: HttpClient) {
  }
  updateUser(user: User) {
    return this.http.put<User>('api/user/update', user).pipe(
      catchError(this.handleError<string>('signUp'))
    );
  }
  createUser(user: User) {
    return this.http.post<User>('api/user/create', user).pipe(
      catchError(this.handleError<string>('signUp'))
    );
  }
  //user id!!!!
  deleteUser(user: User) {
    return this.http.delete<User>('api/user/delete/' + user).pipe(
      catchError(this.handleError<string>('signUp'))
    );
  }
  signUp(user: User): Observable<any> {
    return this.http.post<User>('api/signup', user).pipe(
      catchError(this.handleError<string>('signUp'))
    );

  }
  login(user): Observable<any> {
    return this.http.post<any>('api/login', user);
  }

  private handleError<T>(operation= 'opeartion') {
    return (error: any): Observable<T> => {
      return of(error.text);
    };
  }


}
