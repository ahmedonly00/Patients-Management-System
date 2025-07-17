package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.Payment;
import com.patients.patientsMgt.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
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
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    @PutMapping(value = "/updatePayment/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        payment.setPaymentId(id);
        return paymentService.savePayment(payment);
    }

    @DeleteMapping(value = "/deletePayment  /{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
} 