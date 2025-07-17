package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.Insurance;
import com.patients.patientsMgt.services.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/insurances")
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
