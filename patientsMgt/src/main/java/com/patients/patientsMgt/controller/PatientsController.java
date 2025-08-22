package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Map<String, Object>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Patients> patientsPage = patientsService.getAllPatients(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Patients", patientsPage.getContent());
        response.put("CurrentPage", patientsPage.getNumber());
        response.put("TotalItems", patientsPage.getTotalElements());
        response.put("TotalPages", patientsPage.getTotalPages());

        return ResponseEntity.ok(response);
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