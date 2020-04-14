import { Component } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import { catchError, tap } from 'rxjs/operators';
import {AppService} from "./app.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'ui-app';
  constructor( private http: HttpClient, private app: AppService, private router: Router) {
    this.app.authenticate(undefined, undefined);
  }
  logout() {
    this.http.post('logout', {}).pipe(tap(() => {
      this.app.authenticated = false;
      this.router.navigateByUrl('/login');
    })).subscribe();

  }
}
