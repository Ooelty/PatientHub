package com.example.heartdiseaseapp.services;

import org.springframework.data.domain.Page;

import com.example.heartdiseaseapp.dtos.PatientDto;
import com.example.heartdiseaseapp.exceptions.PatientException;

public interface PatientService {
    //methode qui retourne la liste des patients
    Page<PatientDto> listofpatients(org.springframework.data.domain.Pageable pageable);
    //chercher un patient avec son id 
    PatientDto getPatient(Long id)throws PatientException;
    void deletePatients(Long id) throws PatientException;
    PatientDto addPatients(PatientDto patientdto);
}
