package com.example.HeartdiseaseApp.services;



import org.springframework.data.domain.Page;

import com.example.HeartdiseaseApp.entities.Patients;
import com.example.HeartdiseaseApp.exceptions.PatientException;

public interface PatientService {
//methode qui retourne la liste des patients
Page<Patients> listofpatients(org.springframework.data.domain.Pageable pageable);
//chercher un patient avec son id 
Patients getPatient(Long id)throws PatientException;
Patients deletePatients(Long id) throws PatientException;
Patients addPatients(Patients patient);
}
