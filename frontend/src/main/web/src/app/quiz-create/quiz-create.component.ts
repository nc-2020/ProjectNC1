import {Component, OnInit} from '@angular/core';
import {QuizService} from "../services/quiz.service";
import {Quiz} from "../entities/quiz";
import {CategoryService} from "../services/category.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from '@angular/common';
import {UserService} from "../user.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-quiz-create',
  templateUrl: './quiz-create.component.html',
  styleUrls: ['./quiz-create.component.css']
})

export class QuizCreateComponent implements OnInit {
  quizForm = this.fb.group({
    'title': ['', [Validators.required, Validators.maxLength(20)]],
    'description': ['', Validators.required, Validators.maxLength(30)]
  })

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private route: ActivatedRoute,
    private router: Router,
    private quizService: QuizService,
    private userService: UserService,
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    // this.getData();
  }

  goBack(): void {
    this.location.back();
  }

  createQuiz() {
    console.log("quiz created");
    this.quizService.createQuiz({
      title: this.quizForm.get('title').value,
      description: this.quizForm.get('description').value,
      user_id: this.userService.user.id
    } as Quiz)
      .subscribe(data  => {
        this.router.navigate(['/quiz-edit/' + data.id ]);
      });
  }

  submit() {
    console.log(this.quizForm);
  }
}
