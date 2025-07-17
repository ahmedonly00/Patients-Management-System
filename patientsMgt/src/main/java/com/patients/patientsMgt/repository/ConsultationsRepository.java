package com.patients.patientsMgt.repository;

import java.util.*;


import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Department;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ConsultationsRepository extends JpaRepository<Consultations, Long> {
    List<Consultations> findByConsultationId(Long consultationId);
    List<Consultations> findByPatient(Patients patient);
    List<Consultations> findByDoctor(Doctors doctor);
    List<Consultations> findByDepartment(Department department);
    List<Consultations> findByConsultationDate(String consultationDate);
    List<Consultations> findByDiagnosis(String diagnosis);
    List<Consultations> findByStatus(Consultations.Status status);
}