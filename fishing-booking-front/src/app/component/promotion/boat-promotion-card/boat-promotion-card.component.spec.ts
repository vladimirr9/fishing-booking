import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoatPromotionCardComponent } from './boat-promotion-card.component';

describe('BoatPromotionCardComponent', () => {
  let component: BoatPromotionCardComponent;
  let fixture: ComponentFixture<BoatPromotionCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoatPromotionCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoatPromotionCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
