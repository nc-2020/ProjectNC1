import {Component, OnInit, Input, ContentChild} from '@angular/core';
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
edit = false;
  @Input()
  user: User;


  constructor(private userService: UserService, private router: Router,private sharedData: SharedUserDataService) { }

  ngOnInit(): void {

  }
  goToProfile() {
    this.edit = true;
  }
  userRole() {
    return this.userService.user.role.name;
  }

}
