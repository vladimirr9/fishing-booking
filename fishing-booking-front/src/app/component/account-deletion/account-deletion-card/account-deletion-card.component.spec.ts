import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountDeletionCardComponent } from './account-deletion-card.component';

describe('AccountDeletionCardComponent', () => {
  let component: AccountDeletionCardComponent;
  let fixture: ComponentFixture<AccountDeletionCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AccountDeletionCardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountDeletionCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
