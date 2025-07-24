package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patients.patientsMgt.dto.AppointmentDTO;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.services.AppointmentsService;
import com.patients.patientsMgt.services.PatientsService;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private PatientsService patientsService;

    @Autowired
    private AppointmentsService appointmentService;

    
    @GetMapping(value = "/all")
    public List<Patients> getAllPatients() {
        return patientsService.getAllPatients();
    }

    @GetMapping(value = "getPatientById/{id}")
    public Optional<Patients> getPatientById(@PathVariable Long id) {
        return patientsService.getPatientById(id);
    }

    @PostMapping(value = "/createPatient")
    public Patients createPatient(@RequestBody Patients patient) {
        return patientsService.savePatient(patient);
    }

    @PutMapping(value = "/updatePatient/{id}")
    public Patients updatePatient(@PathVariable Long id, @RequestBody Patients patient) {
        patient.setPatientId(id);
        return patientsService.savePatient(patient);
    }

    @DeleteMapping(value = "/deletePatient/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientsService.deletePatient(id);
    }

    @PostMapping("/book-appointment")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentDTO appointmentDTO, Principal principal) {
        String username = principal.getName();
        appointmentService.bookAppointmentAsPatient(username, appointmentDTO);
        return ResponseEntity.ok("Appointment booked successfully.");
    }

} 