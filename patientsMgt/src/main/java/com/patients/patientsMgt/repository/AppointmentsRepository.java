package com.patients.patientsMgt.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patients.patientsMgt.model.Appointments;
import com.patients.patientsMgt.model.Appointments.Status;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Patients;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
    List<Appointments> findByAppointmentId(Long appointmentId);
    List<Appointments> findByPatient(Patients patient);
    List<Appointments> findByDoctor(Doctors doctor);
    List<Appointments> findByDoctorAndAppointmentDate(Doctors doctor, LocalDate today);
    List<Appointments> findByPatientAndStatusNot(Patients patient, Status completed);
}
