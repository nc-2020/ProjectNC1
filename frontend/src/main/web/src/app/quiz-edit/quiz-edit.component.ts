import {Component, Input, OnInit} from '@angular/core';
import {Quiz} from "../entities/quiz";

@Component({
  selector: 'app-quiz-edit',
  templateUrl: './quiz-edit.component.html',
  styleUrls: ['./quiz-edit.component.css']
})
export class QuizEditComponent implements OnInit {
  @Input() quiz: Quiz;
  constructor() { }

  ngOnInit(): void {
  }

}
