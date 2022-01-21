import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewComplaintDialogComponent } from './view-complaint-dialog.component';

describe('ViewComplaintDialogComponent', () => {
  let component: ViewComplaintDialogComponent;
  let fixture: ComponentFixture<ViewComplaintDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewComplaintDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewComplaintDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
