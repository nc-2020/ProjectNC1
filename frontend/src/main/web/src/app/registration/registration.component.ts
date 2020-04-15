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
  user: User = new User();

  constructor(private app: AppService, private router: Router) { }

  ngOnInit(): void {
    this.registrationForm = new FormGroup({
      userName: new FormControl(''),
      firstName: new FormControl(''),
      lastName: new FormControl(''),
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
    this.user.userName = this.registrationForm.get('userName').value;
    this.user.firstName = this.registrationForm.get('firstName').value;
    this.user.lastName = this.registrationForm.get('lastName').value;
    this.user.email = this.registrationForm.get('email').value;
    this.user.password = this.registrationForm.get('password').value;
    this.app.signUp(this.user,() => {
      this.router.navigateByUrl('/login');
    });
  }
}
