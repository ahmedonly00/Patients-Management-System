package com.patients.patientsMgt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Users;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Long> {
    List<Doctors> findByDoctorId(Long doctorId);
    List<Doctors> findByFullName(String fullName);
    Optional<Doctors> findByUser(Users user);
    Optional<Doctors> findByEmail(String email);
}


