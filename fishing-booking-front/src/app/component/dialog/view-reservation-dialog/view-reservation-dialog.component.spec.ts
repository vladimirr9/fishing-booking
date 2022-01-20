import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewReservationDialogComponent } from './view-reservation-dialog.component';

describe('ViewReservationDialogComponent', () => {
  let component: ViewReservationDialogComponent;
  let fixture: ComponentFixture<ViewReservationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewReservationDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewReservationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
