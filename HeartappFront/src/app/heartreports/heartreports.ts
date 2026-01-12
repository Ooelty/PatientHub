import { DecimalPipe, isPlatformBrowser } from '@angular/common';
import { Component, CUSTOM_ELEMENTS_SCHEMA, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-heartreports',
  imports: [RouterLink, DecimalPipe],
  templateUrl: './heartreports.html',
  styleUrl: './heartreports.css',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Heartreports implements OnInit {
  isBrowser = false;

  stats = {
    cardioPositive: 32000,
    cardioNegative: 38000,
    male: 36000,
    female: 34000,
    smokers: 21000,
    nonSmokers: 49000,
    active: 42000,
    inactive: 28000
    
  };

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }
  get totalPatients() {
  return this.stats.active + this.stats.inactive; // pour le graphe Mode de Vie
}
}