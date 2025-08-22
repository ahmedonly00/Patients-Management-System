package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.patients.patientsMgt.dto.PatientInsuranceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.dto.InsuranceDTO;
import com.patients.patientsMgt.model.Insurance;
import com.patients.patientsMgt.services.InsuranceService;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllInsurances(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Insurance> insurancePage = insuranceService.getAllInsurances(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Insurances", insurancePage.getContent());
        response.put("CurrentPage", insurancePage.getNumber());
        response.put("TotalItems", insurancePage.getTotalElements());
        response.put("TotalPages", insurancePage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "getInsuranceById/{id}")
    public Optional<Insurance> getInsuranceById(@PathVariable Long id) {
        return insuranceService.getInsuranceById(id);
    }

    @PostMapping(value = "/createInsurance")
    public Insurance createInsurance(@RequestBody Insurance insurance) {
        return insuranceService.saveInsurance(insurance);
    }

    @PutMapping(value = "/updateInsurance/{id}")
    public Insurance updateInsurance(@PathVariable Long id, @RequestBody Insurance insurance) {
        insurance.setInsuranceId(id);
        return insuranceService.saveInsurance(insurance);
    }

    @DeleteMapping(value = "/deleteInsurance/{id}")
    public void deleteInsurance(@PathVariable Long id) {
        insuranceService.deleteInsurance(id);
    }

    @GetMapping(value = "/getInsuranceByPatient")
    public ResponseEntity<List<PatientInsuranceDTO>> getInsuranceByPatient(Principal principal){

        String patientEmail = principal.getName();
        return ResponseEntity.ok(insuranceService.getInsuranceByPatient(patientEmail));
    }
} 
