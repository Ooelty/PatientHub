package com.example.HeartdiseaseApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HeartdiseaseApp.entities.Patients;

public interface PatientsRepo extends JpaRepository<Patients,Long> {

}
