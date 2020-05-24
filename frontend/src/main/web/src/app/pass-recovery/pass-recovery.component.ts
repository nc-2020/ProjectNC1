import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {UserService} from "../services/user.service";
@Component({
  selector: 'app-pass-recovery',
  templateUrl: './pass-recovery.component.html',
  styleUrls: ['./pass-recovery.component.css']
})
export class PassRecoveryComponent implements OnInit {
  recoveryForm: FormGroup;
  email='';
  error=false;
  success=false;
  constructor(private app: UserService, private router: Router) { }

  ngOnInit(): void {
    this.recoveryForm = new FormGroup({
      email: new FormControl('', [
        Validators.email,
        Validators.required
      ]),
    });
  }

  onSubmit() {
    this.error=false;

    this.email = this.recoveryForm.get('email').value;
    this.recoveryForm.reset();
    this.app.recoveryPassword(this.email).subscribe(
      data=>(this.success=data), error => (this.error = error));
  }
}
