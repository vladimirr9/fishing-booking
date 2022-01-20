import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerCalendarPageComponent } from './boat-owner-calendar-page.component';

describe('BoatOwnerCalendarPageComponent', () => {
  let component: BoatOwnerCalendarPageComponent;
  let fixture: ComponentFixture<BoatOwnerCalendarPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerCalendarPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerCalendarPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
