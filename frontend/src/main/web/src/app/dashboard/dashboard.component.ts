import {Component, OnInit, Input, ViewChild, AfterViewChecked, AfterViewInit} from '@angular/core';
import {UserService} from '../services/user.service';
import { QuizService } from '../services/quiz.service';
import { User } from '../entities/user';
import { Quiz } from '../entities/quiz';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';
import {UserCardComponent} from '../user-card/user-card.component';
import {CategoryService} from '../services/category.service';
import {Category} from '../entities/category';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  tab = '';
  user: User;
  users$: Observable<User[]>;
  quizes$: Observable<Quiz[]>;
  categoriesList: Category[] = [];
  isVisible = true;


  private searchQuizTerms = new Subject<string>();
  private searchUserTerms = new Subject<string>();
  @ViewChild(UserCardComponent, {static: false}) userCardChild: UserCardComponent;

  constructor(private userService: UserService, private quizService: QuizService, private categoryService: CategoryService, private location: Location, private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.getCategories();
    this.tab = this.route.snapshot.paramMap.get('tab');
    this.user = this.tab === 'Profile' ? this.userService.user : {role: {}} as User;
    this.quizes$ = this.searchQuizTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.quizService.searchQuizzes(term)),
    );
    this.users$ = this.searchUserTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((term: string) => this.userService.searchUsers(term)),
    );

  }

  search(term: string): void {
    this.isVisible = false;
    if (this.tab === 'Quizzes') {
      this.searchQuizTerms.next(term);
    } else {
      this.searchUserTerms.next(term);
    }

  }
  profileSet( editOnly?: boolean, user?: User) {
    this.user = (user ? user : {role: {}} as User);
    this.changeTab(editOnly ? 'AddProfile' : 'Profile');
  }

  changeTab(tab: string) {
    this.tab = tab;
    this.router.navigateByUrl(`dashboard/${tab}`);
    this.isVisible = true;
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

  getCategories() {
    this.categoryService.getCategories().subscribe(
      (data: Category[]) => {
        this.categoriesList = data;
      }
    );
  }
}
