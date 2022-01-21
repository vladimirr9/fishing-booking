import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventurePromotionComponent } from './adventure-promotion.component';

describe('AdventurePromotionComponent', () => {
  let component: AdventurePromotionComponent;
  let fixture: ComponentFixture<AdventurePromotionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventurePromotionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventurePromotionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
