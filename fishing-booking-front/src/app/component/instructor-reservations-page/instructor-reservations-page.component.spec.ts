import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorReservationsPageComponent } from './instructor-reservations-page.component';

describe('InstructorReservationsPageComponent', () => {
  let component: InstructorReservationsPageComponent;
  let fixture: ComponentFixture<InstructorReservationsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorReservationsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorReservationsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
