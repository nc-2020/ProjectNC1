import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Quiz } from '../entities/quiz';
import {UserService} from "../services/user.service";
import {QuizService} from "../services/quiz.service";
import {Router} from "@angular/router";
import {Session} from "../entities/session";

@Component({
  selector: 'app-quiz-card',
  templateUrl: './quiz-card.component.html',
  styleUrls: ['./quiz-card.component.css']
})
export class QuizCardComponent implements OnInit {

  @Input() quiz: Quiz;
  @Input() isEdit = false;
  @Output() onChanged = new EventEmitter<Quiz>();

  constructor(private userService: UserService,
              private router: Router,
              private quizService: QuizService) { }

  getUserRole() {
    return this.userService.user.role.name;
  }
  ngOnInit(): void {
  }

  public toggleSelected(quiz: Quiz) {
    quiz.favourite = !quiz.favourite;
  }

  createSession() {
    if (this.getUserRole() === 'user') {
      this.quizService.getSession(this.quiz.id).subscribe((session: Session) => {
        console.log(session);
          this.router.navigate(['/quiz/' + this.quiz.id + '/' + session.id]);
      })
    }
  }
}
