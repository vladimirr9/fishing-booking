import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FishingPromotionCardComponent } from './fishing-promotion-card.component';

describe('FishingPromotionCardComponent', () => {
  let component: FishingPromotionCardComponent;
  let fixture: ComponentFixture<FishingPromotionCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FishingPromotionCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FishingPromotionCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
