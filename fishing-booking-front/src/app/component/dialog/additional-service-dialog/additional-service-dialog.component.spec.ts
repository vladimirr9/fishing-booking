import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdditionalServiceDialogComponent } from './additional-service-dialog.component';

describe('AdditionalServiceDialogComponent', () => {
  let component: AdditionalServiceDialogComponent;
  let fixture: ComponentFixture<AdditionalServiceDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdditionalServiceDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdditionalServiceDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
