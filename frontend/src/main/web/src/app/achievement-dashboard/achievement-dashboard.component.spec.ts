import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AchievementDashboardComponent } from './achievement-dashboard.component';

describe('AchievementDashboardComponent', () => {
  let component: AchievementDashboardComponent;
  let fixture: ComponentFixture<AchievementDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AchievementDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AchievementDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
