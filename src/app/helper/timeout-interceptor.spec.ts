import { TestBed, inject } from '@angular/core/testing';

import { TimeoutInterceptor } from './timeout-interceptor';

describe('TimeoutInterceptor', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TimeoutInterceptor]
    });
  });

  it('should be created', inject([TimeoutInterceptor], (service: TimeoutInterceptor) => {
    expect(service).toBeTruthy();
  }));
});
