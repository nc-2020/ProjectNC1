import {Component, Input, OnInit} from '@angular/core';
import {Achievement} from "../entities/achievement";

@Component({
  selector: 'app-achievements',
  templateUrl: './achievements.component.html',
  styleUrls: ['./achievements.component.css']
})
export class AchievementsComponent implements OnInit {
  @Input()
  achievement: Achievement;
  constructor() { }

  ngOnInit(): void {
  }

}
