package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByInsuranceId(Long insuranceId);
    List<Insurance> findByProviderName(String providerName);
} 