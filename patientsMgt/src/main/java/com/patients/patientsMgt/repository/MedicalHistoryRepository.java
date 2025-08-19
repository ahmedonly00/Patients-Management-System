package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.MedicalHistory;
import com.patients.patientsMgt.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Long > {
    Optional<MedicalHistory> findByPatient(Patients patients);
}
