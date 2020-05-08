import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Quiz } from '../entities/quiz'; 

@Component({
  selector: 'app-quiz-card',
  templateUrl: './quiz-card.component.html',
  styleUrls: ['./quiz-card.component.css']
})
export class QuizCardComponent implements OnInit {

  @Input() quiz: Quiz;
  @Input() isEdit = false;
  @Output() onChanged = new EventEmitter<Quiz>();

  constructor() { }

  ngOnInit(): void {
  }

  public toggleSelected(quiz: Quiz) {
    quiz.favourite = !quiz.favourite;
  }
}
