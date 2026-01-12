import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Healthplans } from './healthplans';

describe('Healthplans', () => {
  let component: Healthplans;
  let fixture: ComponentFixture<Healthplans>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Healthplans]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Healthplans);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
