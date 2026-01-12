import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-patients',
  imports: [RouterLink],
  standalone:true,
  templateUrl: './patients.html',
  styleUrl: './patients.css',
})
export class Patients {

}
