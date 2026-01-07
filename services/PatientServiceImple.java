package com.example.HeartdiseaseApp.services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.HeartdiseaseApp.entities.Patients;
import com.example.HeartdiseaseApp.exceptions.PatientException;
import com.example.HeartdiseaseApp.repositories.PatientsRepo;

@Service

public class PatientServiceImple implements PatientService {
    @Autowired
private PatientsRepo patientrepo;

@Override
public Page<Patients> listofpatients(Pageable pageable) {
    return patientrepo.findAll(pageable);
}

@Override
   public Patients getPatient(Long id) throws PatientException{
    return patientrepo.findById(id).orElseThrow(()-> new PatientException("ce patient n'excite pas") );
   } 

   @Override 
   public Patients deletePatients(Long id) throws PatientException{
      //faut chercher si l'id passe en parametre si oui on execute la supression sinon on aura l'exception
      Patients p=patientrepo.findById(id).orElseThrow(()->new PatientException("ce patient n'existe pas"));
      patientrepo.deleteById(id);
      return p;
   }
   @Override
   public Patients addPatients(Patients patient){
      Patients patients=patientrepo.save(patient);
      return patients;
   }
}
