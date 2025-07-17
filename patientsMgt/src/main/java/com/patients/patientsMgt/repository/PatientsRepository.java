package com.patients.patientsMgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patients.patientsMgt.model.Patients;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {
    List<Patients> findByPatientId(Long patientId);
    List<Patients> findByFullName(String fullName);
}
