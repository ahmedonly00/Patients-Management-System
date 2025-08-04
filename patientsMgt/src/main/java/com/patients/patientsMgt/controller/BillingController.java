package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patients.patientsMgt.dto.BillingDTO;
import com.patients.patientsMgt.services.BillingService;

@RestController
@RequestMapping(value= "/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping(value= "/getBillsByPatient")
    public ResponseEntity<List<BillingDTO>> getBillsByPatient(Principal principal){
        String email = principal.getName();
        return ResponseEntity.ok(billingService.getBillsByPatient(email));
    }

    
}
