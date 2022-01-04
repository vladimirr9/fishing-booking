import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorCalendarPageComponent } from './instructor-calendar-page.component';

describe('InstructorCalendarPageComponent', () => {
  let component: InstructorCalendarPageComponent;
  let fixture: ComponentFixture<InstructorCalendarPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorCalendarPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorCalendarPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
