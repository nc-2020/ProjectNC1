import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  time: string = '23';
  constructor() { }

  ngOnInit(): void {
  }
  start() {

    // let i = 1;
    // setTimeout(function run() {
    //   this.updateTimeLine();
    //   setTimeout(run, 100);
    // }, 100);
  }
  // updateTimeLine() {
  //   this.time += 1;
  // }
}
