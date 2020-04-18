import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserProfileRoutingModule } from './user-profile-routing.module';
import { UserProfileComponent } from './user-profile.component';
import {EditProfileComponent} from "../edit-profile/edit-profile.component";


@NgModule({
  declarations: [UserProfileComponent, EditProfileComponent],
  imports: [
    CommonModule,
    UserProfileRoutingModule
  ]
})
export class UserProfileModule { }
