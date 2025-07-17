package com.patients.patientsMgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patients.patientsMgt.model.Doctors;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Long> {
    List<Doctors> findByDoctorId(Long doctorId);
    List<Doctors> findByName(String name);
}

