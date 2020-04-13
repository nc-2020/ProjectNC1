import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { User } from '../entities/user';
import {AppService} from "../app.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  user: User = new User;
  credentials = {username: '', password: ''};
  check = false;
  userForm = this.fb.group({
    username: [this.credentials.username, [Validators.required, Validators.minLength(3)]],
    password: [this.credentials.password, [Validators.required, Validators.minLength(3)]]
  });

  constructor(private fb: FormBuilder, private app: AppService, private http: HttpClient, private router: Router) {
  }

  login() {
    this.app.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/home');
    });
    return false;
  }
  ngOnInit(): void {
  }
}
