import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Validators } from '@angular/forms';
import { User } from '../entities/user';
import {UserService} from '../user.service';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {tap} from "rxjs/operators";
import {Observable} from "rxjs";
import {stringify} from "querystring";
import * as bcrypt from 'bcryptjs';


@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {


  error = false;

  userForm: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(3)]],
    password: ['', [Validators.required, Validators.minLength(3)]]
  });


  constructor(private fb: FormBuilder, private app: UserService, private http: HttpClient, private router: Router) {
  }

  login() {

    // let salt = bcrypt.genSaltSync(10);
    // let hash = bcrypt.hashSync("B4c0/\/", salt);
    // bcrypt.compare('pop', '$2a$10$asGibUnugEjXloeqMNeOE.XUBk3P36BC.0EfZlw4VFJ3Ld4xNJiea').then((res) => {
    //   this.error = res
    // });;

    this.app.login(this.userForm.value).subscribe(
      res => {this.router.navigateByUrl('/dashboard');}, error => {this.error = error});

  }

  ngOnInit(): void {

  }
}
