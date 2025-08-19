package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.PatientInsurance;
import com.patients.patientsMgt.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientInsuranceRepository extends JpaRepository<PatientInsurance, Long> {
    List<PatientInsurance> findByPatient(Patients patients);
}
