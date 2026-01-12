import { TestBed } from '@angular/core/testing';

import { NewPatient } from './new-patient';

describe('NewPatient', () => {
  let service: NewPatient;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewPatient);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
