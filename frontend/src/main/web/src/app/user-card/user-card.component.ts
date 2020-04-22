import { Component, OnInit, Input } from '@angular/core';
import {UserService} from "../user.service";
import { User } from '../entities/user';
import { Router } from '@angular/router';
import { SharedUserDataService } from '../shared-user-data.service';

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {

  @Input()
  user: User = {
    id:'12',
    username: 'lol',
    firstname: 'lol',
    lastname: 'kjk',
    email: 'mompop@sdasd.com',
    role:'user',
    password: 'lol'
  };
  constructor(private userService: UserService,private router: Router,private sharedData: SharedUserDataService) { }

  ngOnInit(): void {
   
  }
  goToProfile(){
    this.sharedData.setUserData(this.user);
    this.router.navigateByUrl('dashboard/Profile');
    
  }
  userRole() {
    return this.userService.user.role;
  }

}
