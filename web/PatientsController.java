package com.example.HeartdiseaseApp.web;

import org.springframework.web.bind.annotation.RestController;

import com.example.HeartdiseaseApp.entities.Patients;
import com.example.HeartdiseaseApp.exceptions.PatientException;
import com.example.HeartdiseaseApp.services.PatientService;

import lombok.AllArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PatientsController {
    //injection de service
private PatientService patientservice;

//appel a la methode dans la couche service
@GetMapping("/patients")
public Page<Patients> listofp(@RequestParam(defaultValue = "0") int page,
@RequestParam (defaultValue = "20") int size){
    return patientservice.listofpatients(PageRequest.of(page, size));
}
//requete qui fait un recherche d'un patient par son id 
@GetMapping("/searchPatients/{id}")
public Patients SearchPatient(@PathVariable(name="id") Long id) throws PatientException {
    return patientservice.getPatient(id);
}
//supprimer un patient
@DeleteMapping("/patients/{id}")
public void deletepatient(@PathVariable Long id)throws PatientException{
patientservice.deletePatients(id);//avec void on fait pas return
}
//ajouter un patient
@PostMapping("/newpatients")
//la requete post contient un body qui contient la structure d'objet qu'on va saisir
public Patients addingPatients(@RequestBody Patients patient){
return patientservice.addPatients(patient);
}
}





