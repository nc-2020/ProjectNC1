import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizAproveFormComponent } from './quiz-aprove-form.component';

describe('QuizAproveFormComponent', () => {
  let component: QuizAproveFormComponent;
  let fixture: ComponentFixture<QuizAproveFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuizAproveFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuizAproveFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
