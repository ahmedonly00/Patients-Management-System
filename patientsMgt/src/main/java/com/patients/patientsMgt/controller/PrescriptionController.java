package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.model.Prescription;
import com.patients.patientsMgt.services.PrescriptionService;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllPrescriptions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Prescription> prescriptionPage = prescriptionService.getAllPrescriptions(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Prescriptions", prescriptionPage.getContent());
        response.put("CurrentPage", prescriptionPage.getNumber());
        response.put("TotalItems", prescriptionPage.getTotalElements());
        response.put("TotalPages", prescriptionPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "getPrescriptionById/{id}")
    public Optional<Prescription> getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

    @PostMapping(value = "/{consultationId}/createPrescription")
    public ResponseEntity<Prescription> createPrescription(
            @PathVariable Long consultationId,
            @RequestBody PrescriptionDTO dto,
            Principal principal ) {
        String doctorUsername = principal.getName();
        Prescription createPrescription = prescriptionService.createPrescription(consultationId, dto, doctorUsername);
        return ResponseEntity.ok(createPrescription);
    }

    @PutMapping("/{prescriptionId}")
    public ResponseEntity<Prescription> updatePrescription(
            @PathVariable Long prescriptionId,
            @RequestBody PrescriptionDTO dto,
            Principal principal) {

        String doctorUsername = principal.getName();
        Prescription updatedPrescription = prescriptionService.updatePrescription(prescriptionId, dto, doctorUsername);
        return ResponseEntity.ok(updatedPrescription);
    }


    @DeleteMapping(value = "/deletePrescription/{id}")
    public void deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
    }


    @GetMapping(value = "/getPrescriptions")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptions(Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByPatient(email));
    }
} 