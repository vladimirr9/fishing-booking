import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HolidayHousesViewComponent } from './holiday-houses-view.component';

describe('HolidayHousesViewComponent', () => {
  let component: HolidayHousesViewComponent;
  let fixture: ComponentFixture<HolidayHousesViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HolidayHousesViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HolidayHousesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
