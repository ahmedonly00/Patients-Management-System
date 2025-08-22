package com.patients.patientsMgt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.patients.patientsMgt.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.model.Medication;
import com.patients.patientsMgt.services.MedicationService;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllMedications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Medication> medicationPage = medicationService.getAllMedications(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", medicationPage.getContent());
        response.put("CurrentPage", medicationPage.getNumber());
        response.put("TotalItems", medicationPage.getTotalElements());
        response.put("TotalPages", medicationPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "getMedicationById/{id}")
    public Optional<Medication> getMedicationById(@PathVariable Long id) {
        return medicationService.getMedicationById(id);
    }

    @PostMapping(value = "/createMedication")
    public Medication createMedication(@RequestBody Medication medication) {
        return medicationService.saveMedication(medication);
    }

    @PutMapping(value = "/updateMedication/{id}")
    public Medication updateMedication(@PathVariable Long id, @RequestBody Medication medication) {
        medication.setMedicationId(id);
        return medicationService.saveMedication(medication);
    }

    @DeleteMapping(value = "/deleteMedication   /{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }
} 