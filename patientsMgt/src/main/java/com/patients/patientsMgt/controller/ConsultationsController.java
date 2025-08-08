package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.patients.patientsMgt.dto.ConsultationsDTO;
import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.model.Prescription;
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

import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.services.ConsultationsService;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationsController {
    @Autowired
    private ConsultationsService consultationsService;

    @GetMapping(value = "/all")
    public List<Consultations> getAllConsultations() {
        return consultationsService.getAllConsultations();
    }

    @GetMapping(value = "getConsultationById/{id}")
    public Optional<Consultations> getConsultationById(@PathVariable Long id) {
        return consultationsService.getConsultationById(id);
    }

    @PostMapping(value = "/createConsultation")
    public ResponseEntity<Consultations> createConsultation(
            @PathVariable Long appointmentId,
            @RequestBody ConsultationsDTO dto,
            Principal principal ) {

        String doctorUsername = principal.getName();
        Consultations createConsultation = consultationsService.createConsultation(appointmentId, dto, doctorUsername);
        return ResponseEntity.ok(createConsultation);
    }

    @DeleteMapping(value = "/deleteConsultation/{id}")
    public void deleteConsultation(@PathVariable Long id) {
        consultationsService.deleteConsultation(id);
    }
} 