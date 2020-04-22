import { Component, OnInit, OnChanges, SimpleChanges, Input } from '@angular/core';
import {UserService} from "../user.service";
import { User } from '../entities/user';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';
import { SharedUserDataService } from '../shared-user-data.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  @Input()tab :string = 'Quizzes';
  param = '';
  users$: Observable<User[]>;
  private searchTerms = new Subject<string>();

  constructor(private userService: UserService,private location: Location, private route: ActivatedRoute,private router: Router,private sharedData: SharedUserDataService) { }

  ngOnInit(): void {
    this.tab = this.route.snapshot.paramMap.get('tab');
    this.users$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((term: string) => this.userService.searchUsers(term)),
    );
  }
  ngOnChanges(changes: SimpleChanges): void{
   

  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  changeTab(tab: string, param?: string) {
    this.tab = tab;
    this.router.navigateByUrl(`dashboard/${tab}`);
    this.param = param;
  }
  userRole() {
    return this.userService.user.role;
  }
  getUser(empty?: string) {
    if(empty) {
      return {role:'moderator'} as User;
    }
      
    return this.userService.user;
  }
}
