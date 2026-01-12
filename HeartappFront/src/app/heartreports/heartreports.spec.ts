import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Heartreports } from './heartreports';

describe('Heartreports', () => {
  let component: Heartreports;
  let fixture: ComponentFixture<Heartreports>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Heartreports]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Heartreports);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
