package com.patients.patientsMgt.controller;

import java.util.List;
import java.util.Optional;

import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.services.DoctorsService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorsController {
    @Autowired
    private DoctorsService doctorsService;

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping(value = "/all")
    public List<Doctors> getAllDoctors() {
        return doctorsService.getAllDoctors();
    }

    @GetMapping(value = "getDoctorById/{id}")
    public Optional<Doctors> getDoctorById(@PathVariable Long id) {
        return doctorsService.getDoctorById(id);
    }

    @PostMapping(value = "/createDoctor")
    public Doctors createDoctor(@RequestBody Doctors doctor) {
        return doctorsService.saveDoctor(doctor);
    }

    @PutMapping(value = "/updateDoctor/{id}")
    public Doctors updateDoctor(@PathVariable Long id, @RequestBody Doctors doctor) {
        doctor.setDoctorId(id);
        return doctorsService.saveDoctor(doctor);
    }

    @DeleteMapping(value = "/deleteDoctor/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorsService.deleteDoctor(id);
    }


    @PostMapping("/appointments/{appointmentId}/prescriptions")
    public ResponseEntity<String> createPrescription(
            @PathVariable Long appointmentId,
            @RequestBody PrescriptionDTO dto,
            @AuthenticationPrincipal UserDetails doctorUser) {

        prescriptionService.createPrescription(appointmentId, dto, doctorUser.getUsername());

        return ResponseEntity.ok("Prescription created successfully");
    }
} 