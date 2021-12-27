import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountDeletionDialogComponent } from './account-deletion-dialog.component';

describe('AccountDeletionDialogComponent', () => {
  let component: AccountDeletionDialogComponent;
  let fixture: ComponentFixture<AccountDeletionDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountDeletionDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountDeletionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
