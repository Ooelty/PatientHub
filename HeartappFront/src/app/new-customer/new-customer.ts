import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { NewPatient } from '../services-2/new-patient';
import { CommonModule } from '@angular/common';
import { Patient } from '../models/Patient';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-new-customer',
  imports: [ReactiveFormsModule,CommonModule,RouterLink],
  templateUrl: './new-customer.html',
  styleUrl: './new-customer.css',
})
export class NewCustomer {
newpatientformgroup!:FormGroup;
constructor(private fb :FormBuilder,private newpatientservice:NewPatient){}
ngOnInit():void{
  this.newpatientformgroup = this.fb.group({
  age: [null, Validators.required],
  sex: [null, Validators.required],
  weight: [null, Validators.required],
  height: [null, Validators.required],
  ap_hi: [null, Validators.required],
  ap_lo: [null, Validators.required],
  cholesterol: [null, Validators.required],
  gluc: [null, Validators.required],
  smoke: [null, Validators.required],
  alco: [null, Validators.required],
  active: [null, Validators.required],
  cardio: [null, Validators.required]
});

}
//creation de la methode qui ajoute un nouveau patient
handleSavePatient(){
   

  if (this.newpatientformgroup.invalid) {
    alert("Formulaire invalide");
    return;
  }

  const raw = this.newpatientformgroup.value;

  const patient: Patient = {
    id:0, // L'ID sera généré par le backend
    age: Number(raw.age),
    gender: raw.sex === 'Female' ? 1 : 0,
    weight: Number(raw.weight),
    height: Number(raw.height),
    ap_hi: Number(raw.ap_hi),
    ap_lo: Number(raw.ap_lo),
    cholesterol: Number(raw.cholesterol),
    gluc: Number(raw.gluc),
    smoke: Number(raw.smoke),
    alco: Number(raw.alco),
    active: Number(raw.active),
    cardio: Number(raw.cardio)
  };

  console.log("Patient envoyé :", patient);;
    }
  }


 

