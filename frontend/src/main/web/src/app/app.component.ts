import {Component, OnInit, OnDestroy, HostListener} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import { catchError, tap, finalize } from 'rxjs/operators';
import {UserService} from './services/user.service';
import {NotificationService} from './services/notification.service';
import {Notification} from './entities/notification';
import {SettingsService} from "./services/settings.service";
import {QuizService} from "./services/quiz.service";
import {Session} from "./entities/session";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'ui-app';
  notifications: Notification[] = [];

  constructor(private http: HttpClient,
              private userService: UserService,
              private router: Router,
              private notificationService: NotificationService,
              private settingsService: SettingsService ,
              ) {

  }

  ngOnInit(): void {
    if(this.userService.user.role.name === 'user') {
      this.getNotifications();
      this.getNotificationSettings();

    }
  }


  @HostListener('window:beforeunload')
  deleteNotifications() {
    this.notificationService.delete(this.notifications.filter(x => x.seen)).subscribe();
  }
  getNotificationSettings() {
    this.settingsService.getNotificationSettings(+this.userService.user.id).
    subscribe(res => this.settingsService.notificationSettings = res);
  }

  ngOnDestroy(): void {
    localStorage.removeItem('user');

  }
  getNewNotifications(): Notification[] {
    return this.notifications.filter(x => !x.seen);
  }
  getNotifications() {
      this.notificationService.getAll(+this.userService.user.id).
      subscribe(res => this.notifications = res,
      error => this.notifications = []);
  }
  logout() {
    this.userService.logout().subscribe(
      _ => this.router.navigateByUrl('/login'));
  }
  getRole() {
    return this.userService.user.role.name;
  }
  authenticated() {
    return this.userService.authenticated;
  }



}
