import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthorizationComponent} from './authorization/authorization.component';
import {HomeComponent} from './home/home.component';
import {RegistrationComponent} from './registration/registration.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {UserCardComponent} from './user-card/user-card.component';
import {QuizCreateComponent} from './quiz-create/quiz-create.component';
import {QuizEditComponent} from './quiz-edit/quiz-edit.component';
import {QuizDashboardComponent} from './quiz-dashboard/quiz-dashboard.component';
import {DragDropModule} from '@angular/cdk/drag-drop';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AnnouncementComponent} from './announcement/announcement.component';
import {AnnouncementEditComponent} from './announcement-edit/announcement-edit.component';
import {QuizComponent} from './quiz/quiz.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {LoaderService} from './services/loader.service';
import {LoaderInterceptor} from './interceptors/loader-interceptor.service';
import {MyLoaderComponent} from './components/my-loader/my-loader.component';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import { QuizAproveFormComponent } from './quiz-aprove-form/quiz-aprove-form.component';
import { AnnouncementDashboardComponent } from './announcement-dashboard/announcement-dashboard.component';
import {QuizCardComponent} from './quiz-card/quiz-card.component';
import {FriendsComponent} from './friends/friends.component';
import {UserInviteCardComponent} from './friends/user-invite-card/user-invite-card.component';



@NgModule({
  declarations: [
    AppComponent,
    AuthorizationComponent,
    HomeComponent,
    RegistrationComponent,
    DashboardComponent,
    UserProfileComponent,
    UserCardComponent,
    QuizCreateComponent,
    QuizEditComponent,
    QuizDashboardComponent,
    AnnouncementComponent,
    AnnouncementEditComponent,
    QuizComponent,
    MyLoaderComponent,
    QuizCardComponent,
    QuizAproveFormComponent,
    AnnouncementDashboardComponent,
    FriendsComponent,
    UserInviteCardComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    DragDropModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatIconModule,
    MatButtonModule
  ],
  providers: [
    LoaderService,
    { provide: HTTP_INTERCEPTORS, useClass: LoaderInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

