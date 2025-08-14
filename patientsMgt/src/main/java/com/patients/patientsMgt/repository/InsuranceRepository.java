package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.Insurance;
import com.patients.patientsMgt.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByInsuranceId(Long insuranceId);
    List<Insurance> findByProviderName(String providerName);
    List<Insurance> findByPatientUserEmail(String email);
    List<Insurance> findByPatient(Patients patients);

} 