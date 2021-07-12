import { TestBed } from '@angular/core/testing';

import { TraniningsService } from './traninings.service';

describe('TraniningsService', () => {
  let service: TraniningsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TraniningsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
