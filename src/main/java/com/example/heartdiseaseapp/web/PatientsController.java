package com.example.heartdiseaseapp.web;

import org.springframework.web.bind.annotation.RestController;

import com.example.heartdiseaseapp.dtos.PatientDto;
import com.example.heartdiseaseapp.exceptions.PatientException;
import com.example.heartdiseaseapp.services.PatientService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor


public class PatientsController {
    //injection de service
    private PatientService patientservice;

   
    @PreAuthorize("hasRole('ADMIN')")//pour dire que cette methode est accessible que pour les utilisateurs qui ont le role admin
    @GetMapping("/patients")
    public Page<PatientDto> listofp(@RequestParam(defaultValue = "0") int page,
            @RequestParam (defaultValue = "20") int size){
        return patientservice.listofpatients(PageRequest.of(page, size));
    }

    //requete qui fait un recherche d'un patient par son id 
     @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/searchPatients/{id}")
    public PatientDto SearchPatient(@PathVariable(name="id") Long id) throws PatientException {
        return patientservice.getPatient(id);
    }

    //supprimer un patient
     @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/patients/{id}")
    public void deletepatient(@PathVariable Long id)throws PatientException{
        patientservice.deletePatients(id);//avec void on fait pas return
    }

    //ajouter un patient
     @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/newpatients")
    //la requete post contient un body qui contient la structure d'objet qu'on va saisir
    public PatientDto addingPatients(@RequestBody PatientDto patientdto){
        return patientservice.addPatients(patientdto);
    }
    //endpoint pour tester les roles pour chaque jwt d'un client
    @GetMapping("/roles")
public ResponseEntity<?> roles(Authentication authentication) {
    return ResponseEntity.ok(authentication.getAuthorities());
}
    
}
