import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdventureDetailedComponent } from './adventure-detailed.component';

describe('AdventureDetailedComponent', () => {
  let component: AdventureDetailedComponent;
  let fixture: ComponentFixture<AdventureDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdventureDetailedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdventureDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
