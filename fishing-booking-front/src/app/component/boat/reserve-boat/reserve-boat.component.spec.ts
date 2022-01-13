import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReserveBoatComponent } from './reserve-boat.component';

describe('ReserveBoatComponent', () => {
  let component: ReserveBoatComponent;
  let fixture: ComponentFixture<ReserveBoatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReserveBoatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReserveBoatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
