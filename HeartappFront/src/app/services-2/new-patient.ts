import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from '../models/Patient';

@Injectable({
  providedIn: 'root',
})
export class NewPatient {
  constructor(private http:HttpClient){}
  //creaation de service qui lie le back end avec le front end 
  public Addnewpatient(patient:Patient){
    return this.http.post<Patient>('http://localhost:9000/newpatients',patient);
  }
}
