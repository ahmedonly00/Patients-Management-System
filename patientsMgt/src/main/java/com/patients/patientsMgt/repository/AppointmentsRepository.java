package com.patients.patientsMgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patients.patientsMgt.model.Appointments;

import java.util.List;

import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Doctors;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    List<Appointments> findByAppointmentId(Long appointmentId);
    List<Appointments> findByPatient(Patients patient);
    List<Appointments> findByDoctor(Doctors doctor);
}
