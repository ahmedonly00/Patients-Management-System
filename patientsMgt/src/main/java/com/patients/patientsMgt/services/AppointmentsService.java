package com.patients.patientsMgt.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.dto.AppointmentDTO;
import com.patients.patientsMgt.model.Appointments;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.repository.*;

@Service
public class AppointmentsService {
    
    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private PatientsService patientService;

    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private UsersService usersService;


    public List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    public Optional<Appointments> getAppointmentById(Long id) {
        return appointmentsRepository.findById(id);
    }

    public Appointments makeAppointment(Appointments appointment) {
        return appointmentsRepository.save(appointment);
        
    }

    public void bookAppointmentAsPatient(String username, AppointmentDTO appointmentDTO) {
        Users user = usersService.findByUsername(username);
        Patients patient = patientService.findByUser(user);

        if (patient == null) {
            throw new RuntimeException("Patient record not found for user: " + username);
        }

        Appointments appointment = new Appointments();
        appointment.setPatient(patient);
        appointment.setDoctor(null);
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setAppointmentType(appointmentDTO.getAppointmentType());
        appointment.setStatus(Appointments.Status.PENDING);
        appointment.setNotes(appointmentDTO.getNotes());
        appointment.setCreated_at(LocalDate.now());
        appointment.setUpdated_at(LocalDate.now());

        appointmentsRepository.save(appointment);

    }

    public void assignDoctorToAppointment(Long appointmentId, Long doctorId) {
        Appointments appointment = appointmentsRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Doctors doctor = doctorsService.getDoctorById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        appointment.setDoctor(doctor);
        appointment.setStatus(Appointments.Status.ASSIGNED);
        appointment.setUpdated_at(LocalDate.now());

        appointmentsRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentsRepository.deleteById(id);
    }

    public String updateAppointmentStatus(Long id, Appointments.Status status) {
        Optional<Appointments> appointmentOpt = appointmentsRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointments appointment = appointmentOpt.get();
            appointment.setStatus(status);
            appointmentsRepository.save(appointment);
            return "Appointment status updated successfully.";
        } else {
            return "Appointment not found.";
        }
    }

    public List<AppointmentDTO> getUpcomingAppointments(String email) {
    Patients patient = patientsRepository.findByUserEmail(email)
        .orElseThrow(() -> new RuntimeException("Patient not found"));
        
    return appointmentsRepository.findByPatientAndStatusNot(patient, Appointments.Status.COMPLETED)
        .stream()
        .map(p -> new AppointmentDTO(p.getAppointmentDate(), p.getAppointmentTime(), p.getAppointmentType(), p.getStatus(), p.getNotes(), p.getCreated_at(), p.getUpdated_at()))
        .collect(Collectors.toList());
    }

    
    public List<AppointmentDTO> getTodaysAppointments(String email) {
    Doctors doctor = doctorsRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Doctor not found"));

    LocalDate today = LocalDate.now();
    return appointmentsRepository.findByDoctorAndAppointmentDate(doctor, today)
        .stream()
        .map(p -> new AppointmentDTO(p.getAppointmentDate(), p.getAppointmentTime(), p.getAppointmentType(), p.getStatus(), p.getNotes(), p.getCreated_at(), p.getUpdated_at()))
        .collect(Collectors.toList());
    }

}
