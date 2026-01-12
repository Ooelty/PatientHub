import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Patient} from'../models/Patient';
import { PageResponse } from '../models/pagemodel';
@Injectable({
  providedIn: 'root',
})
export class Patientservice {
  constructor(private http:HttpClient){
  }

  //methode pour la liste des patients
  public ListofAllpatients(page:number,size:number){
  
    return this.http.get<PageResponse<Patient>>(
      'http://localhost:9000/patients',{
        params:{//on les ajoute car il existe dans le backend 
          page:page,
          size:size
        }
      }
    )
  }
  //supprimer un patient par son id 
  public DeletePatient(id:number):Observable<void>{
    return this.http.delete<void>('http://localhost:9000/patients'+`/${id}`);
  }
  //cherhcer un patient par son id 
  public SearchPatientById(id:number){
    return this.http.get<Patient>('http://localhost:9000/searchPatients'+`/${id}`);
  }
}



