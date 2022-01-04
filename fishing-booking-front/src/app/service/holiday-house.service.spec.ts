import { TestBed } from '@angular/core/testing';

import { HolidayHouseService } from './holiday-house.service';

describe('HolidayHouseService', () => {
  let service: HolidayHouseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HolidayHouseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
