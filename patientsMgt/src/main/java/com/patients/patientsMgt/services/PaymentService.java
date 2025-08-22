package com.patients.patientsMgt.services;

import com.patients.patientsMgt.dto.BillingDTO;
import com.patients.patientsMgt.dto.PaymentDTO;
import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.model.Billing;
import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Payment;
import com.patients.patientsMgt.repository.ConsultationsRepository;
import com.patients.patientsMgt.repository.PatientsRepository;
import com.patients.patientsMgt.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ConsultationsRepository consultationsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    public Page<Payment> getAllPayments(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return paymentRepository.findAll(pageable);
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public Payment createPayment(Long consultationId, PaymentDTO dto, String patientEmail) {
        Consultations consultations = consultationsRepository.findByConsultationId(consultationId)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "Consultation ID", consultationId));

        Patients patients = patientsRepository.findByUserEmail(patientEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Email", patientEmail));

        Payment payment = new Payment();
        payment.setConsultation(consultations);
        payment.setPatient(patients);
        payment.setPaymentType(dto.getPaymentType());
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setPaymentDate(LocalDate.now());
        payment.setTransactionId(dto.getTransactionId());
        payment.setNotes(dto.getNotes());
        payment.setCreatedAt(LocalDate.now());
        payment.setUpdatedAt(LocalDate.now());

        return paymentRepository.save(payment);
    }
} 