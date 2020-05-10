
import {Component, EventEmitter, Input, Output} from '@angular/core';
import {UserService} from "../user.service";
import {User} from '../entities/user';
import {UserInvite} from "../entities/user-invite";


@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent {
  @Input()
  user: User;
  @Output()
  onChanged = new EventEmitter<User>();
  name: string = this.userService.user.firstName;
  nameSendButton = 'Send invitation';
  clicked: boolean = false;

  constructor(private userService: UserService) { }

  goToProfile() {
    this.onChanged.emit(this.user);
  }
  userRole() {
    return this.userService.user.role.name;
  }

  sendInvite(inviteText) {
    this.clicked = true;
    this.nameSendButton = 'Invitation was sent';
    this.userService.sendUserInvite({
      inviteText: inviteText,
      userIdFrom: +this.userService.user.id,
      userIdTo: +this.user.id,
    } as UserInvite).subscribe(data  => {

    });
  }



}


