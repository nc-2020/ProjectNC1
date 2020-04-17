import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Validators } from '@angular/forms';
import { User } from '../entities/user';
import {AppService} from '../app.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  user: User = {
    username: '',
    firstname: '',
    lastname: '',
    email: '',
    password: ''
  };
  error = false;
  credentials = {username: '', password: ''};
  check = false;
  userForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(3)]],
    password: ['', [Validators.required, Validators.minLength(3)]]
  });


  constructor(private fb: FormBuilder, private app: AppService, private http: HttpClient, private router: Router) {
  }

  login() {
    // this.credentials.password = this.userForm.get('password').value;
    // this.credentials.username = this.userForm.get('username').value;
    if (this.app.login(this.userForm.value)) {
      this.error = false;
    } else {
      this.error = true;
    }
  }
  ngOnInit(): void {

  }
}
