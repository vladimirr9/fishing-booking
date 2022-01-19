import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatPromotionsComponent } from './boat-promotions.component';

describe('BoatPromotionsComponent', () => {
  let component: BoatPromotionsComponent;
  let fixture: ComponentFixture<BoatPromotionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatPromotionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatPromotionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
