import {Component, OnInit, OnDestroy, HostListener} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {UserService} from './services/user.service';
import {NotificationService} from './services/notification.service';
import {Notification} from './entities/notification';
import {WebSocketService} from "./web-socket.service";


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
              private webSocketAPI: WebSocketService) {

  }

  greeting: any;
  name: string='';

  connect() {
    this.webSocketAPI.connect();
  }

  disconnect() {
    this.webSocketAPI.disconnect();
  }

  sendMessage() {
    this.webSocketAPI.send(this.name);
  }


  ngOnInit(): void {

    if(this.userService.user.role.name === 'user') {
      this.getNotifications();
    }
  }

  @HostListener('window:beforeunload')
  deleteNotifications() {
    this.notificationService.delete(this.notifications.filter(x => x.seen)).subscribe();
  }

  ngOnDestroy(): void {
    localStorage.clear();
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
