package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.List;

import com.patients.patientsMgt.dto.ConsultationsDTO;
import com.patients.patientsMgt.model.Billing;
import com.patients.patientsMgt.model.Consultations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/createBilling")
    public ResponseEntity<Billing> createBilling(
            @PathVariable Long consultationId,
            @RequestBody BillingDTO dto,
            Principal principal ) {

        String patientUsername = principal.getName();
        Billing createBilling = billingService.createBilling(consultationId, dto, patientUsername);
        return ResponseEntity.ok(createBilling);
    }

    
}
