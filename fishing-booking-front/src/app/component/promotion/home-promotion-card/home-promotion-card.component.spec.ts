import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePromotionCardComponent } from './home-promotion-card.component';

describe('HomePromotionCardComponent', () => {
  let component: HomePromotionCardComponent;
  let fixture: ComponentFixture<HomePromotionCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePromotionCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomePromotionCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
