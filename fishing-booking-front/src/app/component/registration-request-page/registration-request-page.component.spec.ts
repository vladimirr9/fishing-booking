import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationRequestPageComponent } from './registration-request-page.component';

describe('RegistrationRequestPageComponent', () => {
  let component: RegistrationRequestPageComponent;
  let fixture: ComponentFixture<RegistrationRequestPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationRequestPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationRequestPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
