import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import { User } from '../entities/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  @Input()
  user: User;

  @Input()
  editOnly = false;

  error = '';
  message = '';
  userForm: FormGroup;


  constructor(private fb: FormBuilder, private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.userForm = this.fb.group({
      firstname: [this.user.firstname, [Validators.required, Validators.minLength(3)]],
      lastname: [this.user.lastname, [Validators.required, Validators.minLength(3)]],
      email: [this.user.email, [Validators.required, Validators.minLength(3), Validators.email]],
      username: [this.user.username, [Validators.required, Validators.minLength(3)]],
      password: [this.user.password, [Validators.required, Validators.minLength(3)]],
      role: [this.user.role]
    });

  }
  userRole() {
    return this.userService.user.role.name;
  }
  add() {
    this.user = this.userForm.value;
    this.userService.createUser(this.user).subscribe(response => {this.message = 'User has been added!'},
        error => {this.error = error.message});
  }

  update() {
    this.user.firstname = this.userForm.get('firstname').value;
    this.user.lastname = this.userForm.get('lastname').value;
    this.user.email = this.userForm.get('email').value;
    this.user.username = this.userForm.get('username').value;
    this.user.password = this.userForm.get('password').value;
    this.user.role = this.userForm.get('role').value;
    this.userService.updateUser(this.user).subscribe(response =>{this.user = response; this.message = 'User has been updated!'},error=>{this.error=error.message});
  }
  delete() {
    this.userService.deleteUser(this.user).subscribe(response => {this.message = 'User has been deleted!';
    this.userService.logout().subscribe(
      _ => this.router.navigateByUrl('/login'))},
        error => { this.error = error.message});
  }

}
