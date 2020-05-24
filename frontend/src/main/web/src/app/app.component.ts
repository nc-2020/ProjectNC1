import {Component, OnInit, OnDestroy, HostListener} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {UserService} from './services/user.service';
import {NotificationService} from './services/notification.service';
import {Notification} from './entities/notification';
import {SettingsService} from "./services/settings.service";
import {TranslateService} from "@ngx-translate/core";


import {WebSocketService} from "./web-socket.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy, OnInit{
  title = 'ui-app';
  deletedNotifications: Notification[] = [];

  constructor(private http: HttpClient,
              private userService: UserService,
              private router: Router,
              public translate: TranslateService,
              public notificationService: NotificationService,
              private settingsService: SettingsService,
              private webSocketAPI:WebSocketService) {
  }


  setTranslate(language: string) {
    localStorage.setItem('language', language);
    this.translate.use(language);
    this.settingsService.setLanguage(language, this.userService.user);
  }

  ngOnInit(): void {
    this.webSocketAPI.connect();
    this.webSocketAPI.send("test");
    if(this.userService.user.role.name === 'user' && this.userService.authenticated) {
      this.getNotifications();
    }
    this.translate.use(localStorage.getItem('language'));
  }

  @HostListener('window:beforeunload')
  deleteNotifications() {
    this.notificationService.delete(this.deletedNotifications).
    subscribe(res => {this.deletedNotifications = []});
  }

  ngOnDestroy(): void {
    localStorage.clear();
  }
  getNewNotifications(): Notification[] {
    return this.notificationService.notifications.filter(x => !x.seen);
  }
  getNotifications() {
      this.notificationService.getAll(+this.userService.user.id).
      subscribe(res => {},
      error => this.notificationService.notifications = []);
  }
  logout() {
    this.userService.logout().subscribe(
      _ => {this.router.navigateByUrl('/login');
      this.notificationService.notifications = []});
  }
  getRole() {
    return this.userService.user.role.name;
  }
  authenticated() {
    return this.userService.authenticated;
  }
  connect(){
    this.webSocketAPI.connect();
  }

  disconnect(){
    this.webSocketAPI.disconnect();
  }

  sendMessage(){
    this.webSocketAPI.send("test");
  }

  handleMessage(message){
    console.log(message)
  }
}
