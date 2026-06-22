package com.example.heartdiseaseapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.heartdiseaseapp.entities.Patients;

public interface PatientsRepo extends JpaRepository<Patients,Long> {
//contains=like
}
