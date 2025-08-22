package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.patients.patientsMgt.dto.ConsultationsDTO;
import com.patients.patientsMgt.model.Billing;
import com.patients.patientsMgt.model.Consultations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.dto.BillingDTO;
import com.patients.patientsMgt.services.BillingService;

@RestController
@RequestMapping(value= "/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllBills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Billing> billingPage = billingService.getAllBills(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", billingPage.getContent());
        response.put("CurrentPage", billingPage.getNumber());
        response.put("TotalItems", billingPage.getTotalElements());
        response.put("TotalPages", billingPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value= "/getBillsByPatient")
    public ResponseEntity<List<BillingDTO>> getBillsByPatient(Principal principal){
        String email = principal.getName();
        return ResponseEntity.ok(billingService.getBillsByPatient(email));
    }

    @PostMapping(value = "/{consultationId}/createBilling")
    public ResponseEntity<Billing> createBilling(
            @PathVariable Long consultationId,
            @RequestBody BillingDTO dto,
            Principal principal ) {

        String patientUsername = principal.getName();
        Billing createBilling = billingService.createBilling(consultationId, dto, patientUsername);
        return ResponseEntity.ok(createBilling);
    }

    
}
