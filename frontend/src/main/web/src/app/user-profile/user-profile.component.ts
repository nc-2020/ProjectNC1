import {Component, Input, OnInit} from '@angular/core';
import {UserService} from "../user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  role = '';
  userForm: FormGroup = this.fb.group({
    firstname: ['', [Validators.required, Validators.minLength(3)]],
    lastname: ['', [Validators.required, Validators.minLength(3)]],
    email: ['', [Validators.required, Validators.minLength(3), Validators.email]],
    username: ['', [Validators.required, Validators.minLength(3)]],
    password: ['', [Validators.required, Validators.minLength(3)]]
  });
  @Input()
  editOnly = false;
  constructor(private fb: FormBuilder, private userService: UserService) { }

  ngOnInit(): void {
    this.role = this.userService.role;
  }
  add() {
    this.userService.createUser(this.userForm.value).subscribe();
  }

  update() {
    this.userService.updateUser(this.userForm.value).subscribe();
  }
  delete() {
    this.userService.deleteUser(this.userForm.value).subscribe();
  }

}
