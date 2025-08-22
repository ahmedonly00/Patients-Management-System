package com.patients.patientsMgt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.patients.patientsMgt.model.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.model.PrescriptionDetail;
import com.patients.patientsMgt.services.PrescriptionDetailService;

@RestController
@RequestMapping("/api/prescription-details")
public class PrescriptionDetailController {
    @Autowired
    private PrescriptionDetailService prescriptionDetailService;


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllPrescriptionDetails(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<PrescriptionDetail> prescriptionDetailPage = prescriptionDetailService.getAllPrescriptionDetails(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Insurances", prescriptionDetailPage.getContent());
        response.put("CurrentPage", prescriptionDetailPage.getNumber());
        response.put("TotalItems", prescriptionDetailPage.getTotalElements());
        response.put("TotalPages", prescriptionDetailPage.getTotalPages());

        return ResponseEntity.ok(response);
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