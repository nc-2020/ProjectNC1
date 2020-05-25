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
import {Achievement} from "../entities/achievement";
import {AchievementService} from "../services/achievement.service";
import {UserAchievement} from "../entities/user-achievement";
import {DEBOUNCE_TIME} from "../parameters";

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
  term: string = "";
  selectedCategories: string[] = [];
  //selectedDateOption: number = 4;
  dateFrom: Date;
  dateTo: Date;
  quizUser: string = "";


  private searchQuizTerms = new Subject<any>();
  private searchUserTerms = new Subject<string>();

  constructor(private userService: UserService, private quizService: QuizService, private achievementService: AchievementService,
              private categoryService: CategoryService,
              private location: Location, private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.getCategories();
    this.tab = this.route.snapshot.paramMap.get('tab');
    this.user = this.tab === 'Profile' ? this.userService.user : {role: {}} as User;
    this.quizes$ = this.searchQuizTerms.pipe(
      debounceTime(DEBOUNCE_TIME),
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((obj: any) => this.quizService.searchQuizzes(obj.title, obj.categories, obj.dateFrom, obj.dateTo, obj.user)),
    );
    this.users$ = this.searchUserTerms.pipe(
      debounceTime(DEBOUNCE_TIME),
      distinctUntilChanged(),
      switchMap((term: string) => this.userService.searchUsers(term)),
    );

  }

  search(): void {
    this.isVisible = false;
    if (this.tab === 'Quizzes') {
      this.searchQuizTerms.next({ title: this.term, categories: this.selectedCategories, dateFrom: this.dateFrom, dateTo: this.dateTo, user: this.quizUser });
    } else {
      this.searchUserTerms.next(this.term);
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
    )
  }
}
