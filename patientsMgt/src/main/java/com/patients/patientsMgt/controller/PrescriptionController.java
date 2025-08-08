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

import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.model.Prescription;
import com.patients.patientsMgt.services.PrescriptionService;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping(value = "/all")
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping(value = "getPrescriptionById/{id}")
    public Optional<Prescription> getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }

//    @PostMapping(value = "/createPrescription")
//    public ResponseEntity<Prescription> createPrescription(
//            @PathVariable Long consultationId,
//            @RequestBody PrescriptionDTO dto,
//            Principal principal ) {
//        String doctorUsername = principal.getName();
//        Prescription createPrescription = prescriptionService.createPrescription(consultationId, dto, doctorUsername);
//        return ResponseEntity.ok(createPrescription);
//    }

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