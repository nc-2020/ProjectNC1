import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AchievementCreateComponent } from './achievement-create.component';

describe('AchievementCreateComponent', () => {
  let component: AchievementCreateComponent;
  let fixture: ComponentFixture<AchievementCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AchievementCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AchievementCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
