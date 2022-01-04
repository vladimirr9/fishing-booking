import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationSorterComponent } from './reservation-sorter.component';

describe('ReservationSorterComponent', () => {
  let component: ReservationSorterComponent;
  let fixture: ComponentFixture<ReservationSorterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReservationSorterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationSorterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
