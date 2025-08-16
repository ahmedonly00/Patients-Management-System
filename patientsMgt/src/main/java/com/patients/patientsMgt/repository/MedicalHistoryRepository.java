package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Long > {
    List<MedicalHistory> findByPatientUserEmail(String email);
}
