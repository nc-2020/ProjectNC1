import { Component, OnInit } from '@angular/core';
import { User } from '../entities/user';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MustMatchValidator} from './_helpers/must-match.validator';
import {Router} from "@angular/router";
import {AppService} from "../app.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm: FormGroup;
  user: User = {
    username: '',
    firstname: '',
    lastname: '',
    email: '',
    password: ''
  };

  constructor(private app: AppService, private router: Router) { }

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      username: new FormControl(''),
      firstname: new FormControl(''),
      lastname: new FormControl(''),
      email: new FormControl('', [
        Validators.email,
        Validators.required
      ]),
      password: new FormControl(null, [
        Validators.required,
        Validators.minLength(3)
      ]),
      confirmPassword: new FormControl(null, [
        Validators.required,
        Validators.minLength(3),
      ]),
      checkAgreement: new FormControl([true,
        Validators.requiredTrue
      ])
    }, {validators: MustMatchValidator.passwordConfirming});
  }

  onSubmit() {
    console.log(this.user);
    this.user.username = this.registrationForm.get('username').value;
    this.user.firstname = this.registrationForm.get('firstname').value;
    this.user.lastname = this.registrationForm.get('lastname').value;
    this.user.email = this.registrationForm.get('email').value;
    this.user.password = this.registrationForm.get('password').value;
    this.app.signUp(this.user).subscribe(
      _ => this.router.navigateByUrl('/login'));
  }
}
