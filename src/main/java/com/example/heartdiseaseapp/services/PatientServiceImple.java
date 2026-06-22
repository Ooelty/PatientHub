package com.example.heartdiseaseapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.heartdiseaseapp.dtos.PatientDto;
import com.example.heartdiseaseapp.entities.Patients;
import com.example.heartdiseaseapp.exceptions.PatientException;
import com.example.heartdiseaseapp.mappers.PatientMapper;
import com.example.heartdiseaseapp.repositories.PatientsRepo;

@Service
public class PatientServiceImple implements PatientService {
    @Autowired
    private PatientsRepo patientrepo;
    //il est importatnt d'injecter le mapper
    @Autowired
    private PatientMapper patientMapper;
        
    ///tous les methodes ici vont retourner des responses de type PatientDto
    /// on recupere les requests dtos pouis les mapper vers les entités et on fait le contraire pour les responses 
    @Override
    public Page<PatientDto> listofpatients(Pageable pageable) {
        //on recupere la liste des patients pour la transformer en objet dto
        Page<Patients> patients=patientrepo.findAll(pageable);
        //on va transformer la liste des patients en liste des patientdto 
        Page<PatientDto> patientDtos=patients.map(patients1->patientMapper.fromPatient(patients1));
        return patientDtos ;
       }

    @Override
    public PatientDto getPatient(Long id) throws PatientException{
       Patients p=patientrepo.findById(id).orElseThrow(()->new PatientException("ce patient n'existe pas"));
       return patientMapper.fromPatient(p);
    }


    @Override 
    public void deletePatients(Long id) throws PatientException{
        Patients p = patientrepo.findById(id).orElseThrow(()->new PatientException("ce patient n'existe pas"));
         patientrepo.delete(p);
       
    }

    @Override
    public PatientDto addPatients(PatientDto patientdto){
       Patients patient=patientMapper.fromPatients(patientdto);//Mapping vers patientDto
       Patients savedPatientDto=patientrepo.save(patient);
         return patientMapper.fromPatient(savedPatientDto);
    }
}
