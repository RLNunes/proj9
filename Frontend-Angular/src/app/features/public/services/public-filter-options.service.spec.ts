import { TestBed } from '@angular/core/testing';

import { PublicFilterOptionsService } from './public-filter-options.service';

describe('PublicFilterOptionsService', () => {
  let service: PublicFilterOptionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PublicFilterOptionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
