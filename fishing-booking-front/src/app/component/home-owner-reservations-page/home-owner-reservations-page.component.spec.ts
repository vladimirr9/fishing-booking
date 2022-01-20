import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerReservationsPageComponent } from './home-owner-reservations-page.component';

describe('HomeOwnerReservationsPageComponent', () => {
  let component: HomeOwnerReservationsPageComponent;
  let fixture: ComponentFixture<HomeOwnerReservationsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerReservationsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerReservationsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
