package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.PrescriptionDetail;
import com.patients.patientsMgt.services.PrescriptionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prescription-details")
public class PrescriptionDetailController {
    @Autowired
    private PrescriptionDetailService prescriptionDetailService;

    @GetMapping(value = "/all")
    public List<PrescriptionDetail> getAllPrescriptionDetails() {
        return prescriptionDetailService.getAllPrescriptionDetails();
    }

    @GetMapping(value = "getPrescriptionDetailById/{id}")
    public Optional<PrescriptionDetail> getPrescriptionDetailById(@PathVariable Long id) {
        return prescriptionDetailService.getPrescriptionDetailById(id);
    }

    @PostMapping(value = "/createPrescriptionDetail")
    public PrescriptionDetail createPrescriptionDetail(@RequestBody PrescriptionDetail prescriptionDetail) {
        return prescriptionDetailService.savePrescriptionDetail(prescriptionDetail);
    }

    @PutMapping(value = "/updatePrescriptionDetail/{id}")
    public PrescriptionDetail updatePrescriptionDetail(@PathVariable Long id, @RequestBody PrescriptionDetail prescriptionDetail) {
        prescriptionDetail.setPrescriptionDetailId(id);
        return prescriptionDetailService.savePrescriptionDetail(prescriptionDetail);
    }

    @DeleteMapping(value = "/deletePrescriptionDetail/{id}")
    public void deletePrescriptionDetail(@PathVariable Long id) {
        prescriptionDetailService.deletePrescriptionDetail(id);
    }
} 