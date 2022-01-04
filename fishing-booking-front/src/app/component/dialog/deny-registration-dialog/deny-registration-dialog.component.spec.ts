import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DenyRegistrationDialogComponent } from './deny-registration-dialog.component';

describe('DenyRegistrationDialogComponent', () => {
  let component: DenyRegistrationDialogComponent;
  let fixture: ComponentFixture<DenyRegistrationDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DenyRegistrationDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DenyRegistrationDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
