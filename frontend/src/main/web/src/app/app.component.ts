import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import { catchError, tap, finalize } from 'rxjs/operators';
import {UserService} from "./services/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'ui-app';


  constructor(private http: HttpClient, private app: UserService, private router: Router) {
    this.app.login(undefined);

  }

  logout() {
    this.app.logout().subscribe(
      _ => this.router.navigateByUrl('/login'));
  }
  authenticated() {
    return this.app.authenticated;
  }

}
