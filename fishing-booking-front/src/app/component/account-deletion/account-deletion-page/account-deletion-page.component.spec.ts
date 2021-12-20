import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountDeletionPageComponent } from './account-deletion-page.component';

describe('AccountDeletionPageComponent', () => {
  let component: AccountDeletionPageComponent;
  let fixture: ComponentFixture<AccountDeletionPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountDeletionPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountDeletionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
