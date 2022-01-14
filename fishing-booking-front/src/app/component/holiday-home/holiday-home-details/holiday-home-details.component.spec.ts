import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HolidayHomeDetailsComponent } from './holiday-home-details.component';

describe('HolidayHomeDetailsComponent', () => {
  let component: HolidayHomeDetailsComponent;
  let fixture: ComponentFixture<HolidayHomeDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HolidayHomeDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HolidayHomeDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
