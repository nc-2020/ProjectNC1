import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import {  } from './user-activity.component';
import {UserActivity} from "../entities/user-activity";
import {UserActivityComponent} from "./user-activity.component";

describe('User', () => {
  let component: UserActivityComponent;
  let fixture: ComponentFixture<UserActivityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserActivityComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserActivityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
