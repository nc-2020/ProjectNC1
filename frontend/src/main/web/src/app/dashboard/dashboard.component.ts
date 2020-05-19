import {Component, OnInit, Input, ViewChild, AfterViewChecked, AfterViewInit, ElementRef} from '@angular/core';
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
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Session} from "../entities/session";
import {DEBOUNCE_TIME} from "../parameters";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  @ViewChild('closeModal') closeModal: ElementRef;
  tab = '';
  user: User;
  users$: Observable<User[]>;
  quizes$: Observable<Quiz[]>;
  categoriesList: Category[] = [];
  isVisible = true;
  joinForm: FormGroup;
  message='';

  private searchQuizTerms = new Subject<string>();
  private searchUserTerms = new Subject<string>();

  constructor(private userService: UserService, private quizService: QuizService, private achievementService: AchievementService,
              private categoryService: CategoryService,
              private location: Location, private route: ActivatedRoute,
              private router: Router,
              private fb: FormBuilder,
    ) { }

  ngOnInit(): void {
    this.getCategories();
    this.tab = this.route.snapshot.paramMap.get('tab');
    this.user = this.tab === 'Profile' ? this.userService.user : {role: {}} as User;
    this.quizes$ = this.searchQuizTerms.pipe(
      debounceTime(DEBOUNCE_TIME),
      distinctUntilChanged(),
      switchMap((term: string) => this.quizService.searchQuizzes(term)),
    );
    this.users$ = this.searchUserTerms.pipe(
      debounceTime(DEBOUNCE_TIME),
      distinctUntilChanged(),
      switchMap((term: string) => this.userService.searchUsers(term)),
    );
    this.setJoinForm();

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
    )
  }

  private setJoinForm() {
    this.joinForm = this.fb.group({
      accessCode: ["", [Validators.required, Validators.minLength(3)]]
    } );
  }
  connectToSession(accessCode:string){
    // this.userService.user.joined=true;
    this.quizService.joinSession(accessCode).subscribe((session: Session) => {
     this.router.navigate(['/quiz/' + session.quiz_id + '/' + session.id]);
      this.closeModal.nativeElement.click()
    }, error => this.message = error.message)
  }

  submit() {
    this.connectToSession(this.joinForm.get('accessCode').value);
  }


}
