import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  authenticated = false;


  constructor(private http: HttpClient) {
  }
  signUp(user, callback) {
    this.http.post('api/signup', user).subscribe(response => {
      if (response['name']) {
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });

  }
  login(user, callback) {

    // const headers = new HttpHeaders(credentials ? {
    //   authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    // } : {});

    this.http.get('api/login', user).subscribe(response => {
      if (response['name']) {
        this.authenticated = true;
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });

  }

  // authenticate(credentials, callback) {
  //
  //   const headers = new HttpHeaders(credentials ? {
  //     authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
  //   } : {});
  //
  //   this.http.get('user', { headers: headers}).subscribe(response => {
  //     if (response['name']) {
  //       this.authenticated = true;
  //     } else {
  //       this.authenticated = false;
  //     }
  //     return callback && callback();
  //   });
  //
  // }
}
