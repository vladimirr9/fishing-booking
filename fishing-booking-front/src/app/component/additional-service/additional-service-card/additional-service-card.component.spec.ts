import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionalServiceCardComponent } from './additional-service-card.component';

describe('AdditionalServiceCardComponent', () => {
  let component: AdditionalServiceCardComponent;
  let fixture: ComponentFixture<AdditionalServiceCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdditionalServiceCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdditionalServiceCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
