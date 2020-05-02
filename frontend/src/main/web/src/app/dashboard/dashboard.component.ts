import {Component, OnInit, Input, ViewChild, AfterViewChecked, AfterViewInit} from '@angular/core';
import {UserService} from "../user.service";
import { User } from '../entities/user';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';
import { SharedUserDataService } from '../shared-user-data.service';
import {UserCardComponent} from "../user-card/user-card.component";
import {UserProfileComponent} from "../user-profile/user-profile.component";
import * as bcrypt from 'bcryptjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  tab: string = '';
  user: User;
  users$: Observable<User[]>;

  private searchTerms = new Subject<string>();
  @ViewChild(UserCardComponent, {static: false}) userCardChild: UserCardComponent;

  constructor(private userService: UserService, private location: Location, private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.tab = this.route.snapshot.paramMap.get('tab');
    this.user = this.userService.user;
    this.users$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((term: string) => this.userService.searchUsers(term)),
    );
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }
  profileSet( editOnly?: boolean, user?: User)
  {
    this.user = (user ? user : {role: {name: 'moderator'}} as User)
    this.changeTab(editOnly ? 'AddProfile' : 'Profile');
  }

  changeTab(tab: string) {
    this.tab = tab;
    this.router.navigateByUrl(`dashboard/${tab}`);
  }

  getUser() {
    return this.userService.user;
  }

  getUserName() {
    return this.userService.user.firstName;
  }
  getUserLastName() {
    return this.userService.user.lastName;
  }
  getUserRole() {
    return this.userService.user.role.name;
  }
}
