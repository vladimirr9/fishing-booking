import { TestBed } from '@angular/core/testing';

import { ProviderRegistrationService } from './provider-registration.service';

describe('ProviderRegistrationService', () => {
  let service: ProviderRegistrationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProviderRegistrationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
