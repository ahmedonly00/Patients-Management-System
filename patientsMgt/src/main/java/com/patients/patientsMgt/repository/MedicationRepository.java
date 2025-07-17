package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByMedicationId(Long medicationId);
    List<Medication> findByMedicationName(String medicationName);
} 