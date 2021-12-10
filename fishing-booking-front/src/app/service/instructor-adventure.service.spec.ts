import { TestBed } from '@angular/core/testing';

import { InstructorAdventureService } from './instructor-adventure.service';

describe('InstructorAdventureService', () => {
  let service: InstructorAdventureService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InstructorAdventureService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
