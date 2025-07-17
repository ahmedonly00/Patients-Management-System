package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDate;

import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Doctors;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findByPrescriptionId(Long prescriptionId);
    List<Prescription> findByPatient(Patients patient);
    List<Prescription> findByDoctor(Doctors doctor);
    List<Prescription> findByPrescriptionDate(LocalDate prescriptionDate);
} 