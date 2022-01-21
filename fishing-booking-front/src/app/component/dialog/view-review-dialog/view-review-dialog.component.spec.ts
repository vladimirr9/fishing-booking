import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewReviewDialogComponent } from './view-review-dialog.component';

describe('ViewReviewDialogComponent', () => {
  let component: ViewReviewDialogComponent;
  let fixture: ComponentFixture<ViewReviewDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewReviewDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewReviewDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
