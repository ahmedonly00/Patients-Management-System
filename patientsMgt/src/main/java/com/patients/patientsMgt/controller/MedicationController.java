package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.Medication;
import com.patients.patientsMgt.services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medications")
public class MedicationController {
    @Autowired
    private MedicationService medicationService;

    @GetMapping(value = "/all")
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
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