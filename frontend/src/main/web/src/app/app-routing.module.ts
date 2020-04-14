import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthorizationComponent} from "./authorization/authorization.component";
import {HomeComponent} from "./home/home.component";
import {AppComponent} from "./app.component";
import {RegistrationComponent} from "./registration/registration.component";


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/login'},
  // { path: '', component: AppComponent},
  { path: 'home', component: HomeComponent},
  { path: 'login', component: AuthorizationComponent},
  { path: 'signup', component: RegistrationComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
