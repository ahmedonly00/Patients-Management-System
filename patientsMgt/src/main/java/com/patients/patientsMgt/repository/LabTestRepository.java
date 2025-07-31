package com.patients.patientsMgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patients.patientsMgt.model.LabTest;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Long> {
    
    
}
