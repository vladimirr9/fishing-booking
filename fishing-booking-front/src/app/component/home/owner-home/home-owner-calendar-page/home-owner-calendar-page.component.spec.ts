import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeOwnerCalendarPageComponent } from './home-owner-calendar-page.component';

describe('HomeOwnerCalendarPageComponent', () => {
  let component: HomeOwnerCalendarPageComponent;
  let fixture: ComponentFixture<HomeOwnerCalendarPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeOwnerCalendarPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeOwnerCalendarPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
