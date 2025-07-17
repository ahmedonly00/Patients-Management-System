package com.patients.patientsMgt.repository;

import com.patients.patientsMgt.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByPaymentId(Long paymentId);
    List<Payment> findByPaymentDate(LocalDate paymentDate);
    List<Payment> findByAmount(Double amount);
} 