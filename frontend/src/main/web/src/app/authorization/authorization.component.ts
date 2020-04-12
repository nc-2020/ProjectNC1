import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import { User } from '../entities/user';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-authorization',
  templateUrl: './authorization.component.html',
  styleUrls: ['./authorization.component.css']
})
export class AuthorizationComponent implements OnInit {

  user: User = new User;

  check = false;
  userForm = this.fb.group({
    username: ['', [Validators.required, Validators.minLength(3)]],
    password: ['',[Validators.required,Validators.minLength(3)]]
  });

  constructor(private fb: FormBuilder) { }

  onSubmit(){
    
    this.user.userName = this.userForm.get('username').value;
    this.user.password = this.userForm.get("password").value;
    this.user.email = '';

  }

  ngOnInit(): void {
  }

}
