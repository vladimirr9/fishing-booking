import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatOwnerReservationsPageComponent } from './boat-owner-reservations-page.component';

describe('BoatOwnerReservationsPageComponent', () => {
  let component: BoatOwnerReservationsPageComponent;
  let fixture: ComponentFixture<BoatOwnerReservationsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatOwnerReservationsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatOwnerReservationsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
