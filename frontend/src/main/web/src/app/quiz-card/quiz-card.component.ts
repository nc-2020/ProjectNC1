import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Quiz } from '../entities/quiz';
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-quiz-card',
  templateUrl: './quiz-card.component.html',
  styleUrls: ['./quiz-card.component.css']
})
export class QuizCardComponent implements OnInit {

  @Input() quiz: Quiz;
  @Input() isEdit = false;
  @Output() onChanged = new EventEmitter<Quiz>();

  constructor(private userService: UserService) { }

  getUserRole() {
    return this.userService.user.role.name;
  }
  ngOnInit(): void {
  }

  public toggleSelected(quiz: Quiz) {
    quiz.favourite = !quiz.favourite;
  }
}
