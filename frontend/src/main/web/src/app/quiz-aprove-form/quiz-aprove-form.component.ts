import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {QuizService} from "../services/quiz.service";

@Component({
  selector: 'app-quiz-aprove-form',
  templateUrl: './quiz-aprove-form.component.html',
  styleUrls: ['./quiz-aprove-form.component.css']
})
export class QuizAproveFormComponent implements OnInit {

  @Input()
  quizId: number;
  @Input()
  userId: number;
  approveForm: FormGroup;
  constructor(private fb: FormBuilder, private quizService: QuizService) { }

  ngOnInit(): void {
    this.approveForm = this.fb.group({
      comment: ['', [Validators.required, Validators.minLength(3)]],
      approveStatus: ['', [Validators.required]]}
     );
  }
  submit() {
      // this.quizService.quizApprove({id: this.quizId, description: this.approveForm.get('comment').value,
      //   user_id: this.userId, status: {name: this.approveForm.get('approveStatus').value}}).
      // subscribe();
  }
}
