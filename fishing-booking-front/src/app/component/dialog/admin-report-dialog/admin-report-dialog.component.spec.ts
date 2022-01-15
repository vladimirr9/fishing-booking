import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminReportDialogComponent } from './admin-report-dialog.component';

describe('AdminReportDialogComponent', () => {
  let component: AdminReportDialogComponent;
  let fixture: ComponentFixture<AdminReportDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminReportDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminReportDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
