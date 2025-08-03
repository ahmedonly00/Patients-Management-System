package com.patients.patientsMgt.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Prescription;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPrescriptionId(Long prescriptionId);
    List<Prescription> findByPatient(Patients patient);
    List<Prescription> findByDoctor(Doctors doctor);
    List<Prescription> findByPrescriptionDate(LocalDate prescriptionDate);
    List<Prescription> findByPatientUserEmail(String email);
} 