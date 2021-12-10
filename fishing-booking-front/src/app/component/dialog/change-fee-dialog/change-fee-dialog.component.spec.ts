import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeFeeDialogComponent } from './change-fee-dialog.component';

describe('ChangeFeeDialogComponent', () => {
  let component: ChangeFeeDialogComponent;
  let fixture: ComponentFixture<ChangeFeeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeFeeDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeFeeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
