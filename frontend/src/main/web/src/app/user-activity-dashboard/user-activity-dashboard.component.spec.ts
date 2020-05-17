import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserActivityDashboardComponent } from './user-activity-dashboard.component';

describe('UserActivityDashboardComponent', () => {
  let component: UserActivityDashboardComponent;
  let fixture: ComponentFixture<UserActivityDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserActivityDashboardComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserActivityDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
