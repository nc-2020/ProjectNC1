import {Component, OnInit, Input, ContentChild, EventEmitter, Output} from '@angular/core';
import {UserService} from "../user.service";
import { User } from '../entities/user';
import { Router } from '@angular/router';
import { SharedUserDataService } from '../shared-user-data.service';
import {DashboardComponent} from "../dashboard/dashboard.component";

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {


  @Input()
  user: User;
  @Output()
  onChanged = new EventEmitter<User>();

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {

  }
  goToProfile() {
    this.onChanged.emit(this.user);
  }
  userRole() {
    return this.userService.user.role.name;
  }

}
