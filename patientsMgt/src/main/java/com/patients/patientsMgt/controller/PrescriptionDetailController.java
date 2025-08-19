package com.patients.patientsMgt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patients.patientsMgt.model.PrescriptionDetail;
import com.patients.patientsMgt.services.PrescriptionDetailService;

@RestController
@RequestMapping("/api/prescription-details")
public class PrescriptionDetailController {
    @Autowired
    private PrescriptionDetailService prescriptionDetailService;

    @GetMapping(value = "/all")
    public List<PrescriptionDetail> getAllPrescriptionDetails() {
        return prescriptionDetailService.getAllPrescriptionDetails();
    }

    @GetMapping(value = "getPrescriptionDetailById/{id}")
    public PrescriptionDetail getPrescriptionDetailById(@PathVariable Long id) {
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