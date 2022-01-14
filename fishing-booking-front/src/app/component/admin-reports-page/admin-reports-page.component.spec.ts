import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminReportsPageComponent } from './admin-reports-page.component';

describe('AdminReportsPageComponent', () => {
  let component: AdminReportsPageComponent;
  let fixture: ComponentFixture<AdminReportsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminReportsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminReportsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
