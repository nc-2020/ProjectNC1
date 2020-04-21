import { Component, OnInit } from '@angular/core';
import {UserService} from "../user.service";

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {
  role = 'user';
  constructor(private user: UserService) { }

  ngOnInit(): void {
    this.role = this.user.role;
  }

}
