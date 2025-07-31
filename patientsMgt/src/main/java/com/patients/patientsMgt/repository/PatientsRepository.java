package com.patients.patientsMgt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Users;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long> {
    List<Patients> findByPatientId(Long patientId);
    Optional<Patients> findByUser(Users user);
    Optional<Patients> findByUserEmail(String email);
}
