package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.patients.patientsMgt.dto.ConsultationsDTO;
import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.model.Department;
import com.patients.patientsMgt.model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.services.ConsultationsService;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationsController {
    @Autowired
    private ConsultationsService consultationsService;


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllConsultations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Consultations> consultationsPage = consultationsService.getAllConsultations(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", consultationsPage.getContent());
        response.put("CurrentPage", consultationsPage.getNumber());
        response.put("TotalItems", consultationsPage.getTotalElements());
        response.put("TotalPages", consultationsPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "getConsultationById/{id}")
    public Optional<Consultations> getConsultationById(@PathVariable Long id) {
        return consultationsService.getConsultationById(id);
    }

    @PostMapping(value = "/{appointmentId}/createConsultation")
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