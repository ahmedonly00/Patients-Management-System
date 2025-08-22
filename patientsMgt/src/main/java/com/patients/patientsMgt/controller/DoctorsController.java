package com.patients.patientsMgt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.model.LabTest;
import com.patients.patientsMgt.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Map<String, Object>> getAllDoctors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Doctors> doctorsPage = doctorsService.getAllDoctors(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", doctorsPage.getContent());
        response.put("CurrentPage", doctorsPage.getNumber());
        response.put("TotalItems", doctorsPage.getTotalElements());
        response.put("TotalPages", doctorsPage.getTotalPages());

        return ResponseEntity.ok(response);
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


//    @PostMapping("/createPrescription/{appointmentId}")
//    public ResponseEntity<String> createPrescription(
//            @PathVariable Long appointmentId,
//            @RequestBody PrescriptionDTO dto,
//            @AuthenticationPrincipal UserDetails doctorUser) {
//
//        prescriptionService.createPrescription(appointmentId, dto, doctorUser.getUsername());
//
//        return ResponseEntity.ok("Prescription created successfully");
//    }
} 