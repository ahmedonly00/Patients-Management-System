package com.patients.patientsMgt.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.patients.patientsMgt.dto.BillingDTO;
import com.patients.patientsMgt.dto.PaymentDTO;
import com.patients.patientsMgt.model.Billing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patients.patientsMgt.model.Payment;
import com.patients.patientsMgt.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/all")
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping(value = "getPaymentById/{id}")
    public Optional<Payment> getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping(value = "/createPayment")
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