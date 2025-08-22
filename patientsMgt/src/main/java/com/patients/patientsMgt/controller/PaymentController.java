package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.patients.patientsMgt.dto.BillingDTO;
import com.patients.patientsMgt.dto.PaymentDTO;
import com.patients.patientsMgt.model.Billing;
import com.patients.patientsMgt.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.model.Payment;
import com.patients.patientsMgt.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllPayments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Payment> paymentPage = paymentService.getAllPayments(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", paymentPage.getContent());
        response.put("CurrentPage", paymentPage.getNumber());
        response.put("TotalItems", paymentPage.getTotalElements());
        response.put("TotalPages", paymentPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "getPaymentById/{id}")
    public Optional<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping(value = "/{consultationId}/createPayment")
    public ResponseEntity<Payment> createBilling(
            @PathVariable Long consultationId,
            @RequestBody PaymentDTO dto,
            Principal principal ) {

        String patientUsername = principal.getName();
        Payment createPayment = paymentService.createPayment(consultationId, dto, patientUsername);
        return ResponseEntity.ok(createPayment);
    }

//    @PutMapping(value = "/updatePayment/{id}")
//    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
//        payment.setPaymentId(id);
//        return paymentService.savePayment(payment);
//    }

    @DeleteMapping(value = "/deletePayment  /{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
} 