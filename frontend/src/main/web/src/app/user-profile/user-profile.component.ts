import {Component, ContentChild, Input, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import { User } from '../entities/user';
import { Router } from '@angular/router';
import {DashboardComponent} from "../dashboard/dashboard.component";
import {MustMatchValidator} from "../registration/_helpers/must-match.validator";
import {HashBcrypt} from "../util/hashBcrypt";
import {UploadFilesService} from "../services/upload-files.service";
import {FileValidator} from "./_helpers/file-input.validator";
import {USER_STATUS_ACTIVE, USER_STATUS_DEACTIVE} from '../parameters';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userStatusActive  = USER_STATUS_ACTIVE;
  userStatusDeactive =  USER_STATUS_DEACTIVE;
  @Input()
  user: User;
  @Input()
  editOnly = false;
  error = '';
  message = '';

  uploadResponse = { status: '', message: '', filePath: '' };
  form: FormGroup;
  userForm: FormGroup;
  imageUrl: string = 'https://img.icons8.com/plasticine/100/000000/user-male-circle.png';

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private router: Router,
              private uploadFilesService: UploadFilesService) { }

  ngOnInit(): void {
    if (this.user.image != null) {
      this.imageUrl = this.user.image;
    }
    this.setUserForm();
    this.form = this.fb.group({
      avatar: new FormControl('', [FileValidator.validate, Validators.required])
    });
  }

  onFileChange(event) {
    const _URL = window.URL || window.webkitURL;
    let file;
    if (event.target.files.length > 0) {
      // @ts-ignore
      const fileName = document.getElementById("fileInput").files[0].name;
      const nextSibling = event.target.nextElementSibling;
      nextSibling.innerText = fileName;
      file = event.target.files[0];
    }
    this.form.get('avatar').setValue(file);
  }


  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.form.get('avatar').value);
    this.uploadFilesService.upload(formData).subscribe(
      (res: string) => {
        this.imageUrl = res;
        this.user.image = res;
        console.log(this.user.image);
        this.setImage();
      },
      error => {
        console.log(error.text);
      }
    );
    this.form.reset();
  }


  userRole() {
    return this.userService.user.role.name;
  }


  private setUserForm() {
    this.userForm = this.fb.group({
      firstname: [this.user.firstName, [Validators.required, Validators.minLength(3)]],
      lastname: [this.user.lastName, [Validators.required, Validators.minLength(3)]],
      email: [this.user.email, [Validators.required, Validators.minLength(3), Validators.email]],
      username: [this.user.username, [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(3)]],
      image: [this.user.image],
      confirmPassword: ['', [Validators.required, Validators.minLength(3)]],
      role: [{value: null, disabled: this.userRole() === 'super admin' ? false : true}],
    } );
    this.setProperties();
  }
  private setProperties(): void {
    if (this.editOnly) {
      this.userForm.setValidators(MustMatchValidator.passwordConfirming);
    } else {
     this.userForm.clearValidators();
     this.userForm.get('confirmPassword').clearValidators();
     this.userForm.get('password').clearValidators();
    }
    this.userForm.updateValueAndValidity();
  }
  add() {
    this.clearMessages();
    this.setMaininfo();
    this.user.password = HashBcrypt.hash(this.userForm.get('password').value);
    this.user.role.name = this.userRole() === 'admin' ? 'moderator' : this.userForm.get('role').value;
    this.userService.createUser(this.user).subscribe(
      response => {this.message = 'User has been added!';},
      error => {this.error = error.message});
  }


  setImage() {
    console.log('setImage');
    this.userService.updateUser(this.user).subscribe(
      response => {this.user = response; this.message = 'User has been updated!'},
      error => {this.error = error.message});
  }

  update() {
    this.clearMessages();
    this.setMaininfo();
    this.user.password = this.checkPassword();
    if(!this.error) {
      this.userService.updateUser(this.user).subscribe(
        response => {this.user = response; this.message = 'User has been updated!'},
        error => {this.error = error.message});
    }
  }
  private setMaininfo() {
    this.user.firstName = this.userForm.get('firstname').value;
    this.user.lastName = this.userForm.get('lastname').value;
    this.user.email = this.userForm.get('email').value;
    this.user.username = this.userForm.get('username').value;
  }
  private clearMessages() {
    this.error = '';
    this.message = '';
  }

  private checkPassword() {
    if(this.userForm.get('password').dirty && this.userForm.get('confirmPassword').dirty) {
      if(HashBcrypt.compare(this.userForm.get('confirmPassword').value, this.userService.user.password)){
        return HashBcrypt.hash(this.userForm.get('password').value);
      }
      this.error = 'Password incorrect';
    } else {
      return this.user.password;
    }
  }

  clearPass() {
    this.userForm.get('password').reset();
    this.userForm.get('confirmPassword').reset();
  }
  submit() {

  }

  setStatus(statusId) {
    this.clearMessages();
    this.user.status.id = statusId;
    this.userService.setStatus(statusId, this.user.id).
    subscribe(response => {this.message = 'User has been edited!'},
    error => { this.error = error.error});
  }
}
