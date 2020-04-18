import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthorizationComponent} from "./authorization/authorization.component";
import {HomeComponent} from "./home/home.component";
import {AppComponent} from "./app.component";
import {RegistrationComponent} from "./registration/registration.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {UserProfileComponent} from "./user-profile/user-profile.component"
import {EditProfileComponent} from "./edit-profile/edit-profile.component"

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/home'},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'home', component: HomeComponent},
  { path: 'login', component: AuthorizationComponent},
  { path: 'signup', component: RegistrationComponent },
  { path: 'profile', component: UserProfileComponent },
  { path: 'edit', component: EditProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
