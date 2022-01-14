import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveHolidayHomeComponent } from './reserve-holiday-home.component';

describe('ReserveHolidayHomeComponent', () => {
  let component: ReserveHolidayHomeComponent;
  let fixture: ComponentFixture<ReserveHolidayHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReserveHolidayHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReserveHolidayHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
