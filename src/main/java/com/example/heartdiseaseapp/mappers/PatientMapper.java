package com.example.heartdiseaseapp.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.heartdiseaseapp.dtos.PatientDto;
import com.example.heartdiseaseapp.entities.Patients;

@Service
public class PatientMapper {
//le mapper va permettre de convertir une entité en dto et un dto en entité ou bien le request vers request dto
public PatientDto fromPatient(Patients patient){
PatientDto patientDto=new PatientDto();
BeanUtils.copyProperties(patient, patientDto);//de patient va patientDTO
return patientDto;
}
//ici on va faire le contraire de Patientdto vers Patients mapping vers patients
public Patients fromPatients(PatientDto patientDto){
    Patients patient=new Patients();
    BeanUtils.copyProperties(patientDto, patient);
    return patient;
}
}
