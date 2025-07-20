package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.PrescriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;
    
import com.patients.patientsMgt.model.Prescription;
import com.patients.patientsMgt.model.Medication;

@Repository
public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetail, Long> {
    List<PrescriptionDetail> findByPrescriptionDetailId(Long prescriptionDetailId);
    List<PrescriptionDetail> findByPrescription(Prescription prescription);
    List<PrescriptionDetail> findByMedication(Medication medication);
    List<PrescriptionDetail> findByDosage(String dosage);
    List<PrescriptionDetail> findByFrequency(String frequency);
} 