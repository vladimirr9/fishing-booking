import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HolidayHousesComponent } from './holiday-houses.component';

describe('HolidayHousesComponent', () => {
  let component: HolidayHousesComponent;
  let fixture: ComponentFixture<HolidayHousesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HolidayHousesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HolidayHousesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
