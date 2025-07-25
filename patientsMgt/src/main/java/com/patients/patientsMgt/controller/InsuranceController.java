package com.patients.patientsMgt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patients.patientsMgt.model.Insurance;
import com.patients.patientsMgt.services.InsuranceService;

@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {
    @Autowired
    private InsuranceService insuranceService;

    @GetMapping(value = "/all")
    public List<Insurance> getAllInsurances() {
        return insuranceService.getAllInsurances();
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
} 
