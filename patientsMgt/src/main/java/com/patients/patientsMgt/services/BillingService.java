package com.patients.patientsMgt.services;

import java.util.List;
import java.util.stream.Collectors;

import com.patients.patientsMgt.dto.LabTestDTO;
import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.model.*;
import com.patients.patientsMgt.repository.ConsultationsRepository;
import com.patients.patientsMgt.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.dto.BillingDTO;
import com.patients.patientsMgt.repository.BillingRepository;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private ConsultationsRepository consultationsRepository;

    public Page<Billing> getAllBills(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return billingRepository.findAll(pageable);
    }

    public List<BillingDTO> getBillsByPatient(String email){
        Patients patients = patientsRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Email", email));

        return billingRepository.findByPatientUserEmail(email)
                .stream()
                .map(p -> new BillingDTO(
                    p.getBillingId(), 
                    p.getConsultationFee(), 
                    p.getMedicationCost(), 
                    p.getLabTestFee(), 
                    p.getTotalAmount(), 
                    p.getInsuranceCoverage(), 
                    p.getPatientPayableAmount(), 
                    p.getBillingStatus(), 
                    p.getDueDate(), 
                    p.getCreatedAt(), 
                    p.getUpdatedAt()))
                .collect(Collectors.toList());
        
    }

    public Billing createBilling(Long consultationId, BillingDTO dto, String patientEmail) {
        Consultations consultations = consultationsRepository.findByConsultationId(consultationId)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "ConsultationId", consultationId));

        Patients patients = patientsRepository.findByUserEmail(patientEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Email", patientEmail));

        Billing billing = new Billing();
        billing.setConsultation(consultations);
        billing.setPatient(patients);
        billing.setConsultationFee(dto.getConsultationFee());
        billing.setMedicationCost(dto.getMedicationCost());
        billing.setLabTestFee(dto.getLabTestFee());
        billing.setTotalAmount(dto.getTotalAmount());
        billing.setInsuranceCoverage(dto.getInsuranceCoverage());
        billing.setPatientPayableAmount(dto.getPatientPayableAmount());
        billing.setBillingStatus(dto.getBillingStatus());
        billing.setDueDate(dto.getDueDate());

        return billingRepository.save(billing);
    }

}
