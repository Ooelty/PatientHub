import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Patientservice } from '../services/patientservice';
import { Patient } from '../models/Patient';
import { PageResponse } from '../models/pagemodel';
import { FormBuilder, FormControlStatus, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-liste-patients',
  standalone:true,
  imports: [CommonModule,RouterLink,ReactiveFormsModule,FormsModule],
  templateUrl: './liste-patients.html',
  styleUrl: './liste-patients.css',
})
export class ListePatients implements OnInit{
  Listedespatients:Patient[]=[];
  searchLabel!:FormGroup;
  errormessage:String="cannot find the patient";
  currentPage = 0;
  pageSize = 20;
  totalPages = 0;
  totalElements = 0;
  constructor (private patientservice:Patientservice,private fb:FormBuilder){}
  ngOnInit():void{
    //creation de la place de l'input ou bien le champ
    this.searchLabel = this.fb.group({
    id: [null,Validators.required]
  });


  
    //patients va acceder au service pour appeler la methode qui appel la fct listdepatients de back end
    this.patientservice.ListofAllpatients(this.currentPage,this.pageSize).subscribe({
      next:(response:PageResponse<Patient>)=>{
        this.Listedespatients=response.content;
         this.currentPage = response.page.number;
          this.totalPages = response.page.totalPages;
          this.totalElements = response.page.totalElements;

      },
      error:(err: any)=>{
        console.log(err);
      }
    }) 
  }
  //service pour parcourrir les pages
  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.patientservice.ListofAllpatients(page, this.pageSize).subscribe({
        next: (response: PageResponse<Patient>) => {
          this.Listedespatients = response.content;
          this.currentPage = response.page.number;
        },
        error: (err: any) => {
          console.log(err);
        }
      });
    }
  }
  //service pour supprimer un patient
  DeletePatient(p:Patient):void{
     this.patientservice.DeletePatient(p.id).subscribe({
      next:(data)=>{
        alert("patient deleted");
        this.ngOnInit();//to refresh the list
      },
      error:(err)=>{
        console.log(err);
      }
  });

}
//service pour chercher un patient
handleSearchPatient() {
let id=this.searchLabel?.value.id;
  this.patientservice.SearchPatientById(id).subscribe({
    next: (patient: Patient) => {//en cas de succes
      this.Listedespatients = [patient]; // Mettre à jour la liste avec le patient trouvé
    },
    error: (err) => { //en cas d'erreur
      console.log(err);
      alert(this.errormessage);
    }
  });this.searchLabel.reset();
}

//service pour réinitialiser la recherche et retourner à la liste complète
resetSearch() {
  
  this.currentPage = 0;
  this.patientservice.ListofAllpatients(this.currentPage, this.pageSize).subscribe({
    next: (response: PageResponse<Patient>) => {
      this.Listedespatients = response.content;
      this.currentPage = response.page.number;
      this.totalPages = response.page.totalPages;
      this.totalElements = response.page.totalElements;
    },
    error: (err: any) => {
      console.log(err);
      
    }
  });
}
}

