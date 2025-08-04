package com.patients.patientsMgt.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.dto.BillingDTO;
import com.patients.patientsMgt.repository.BillingRepository;

@Service
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    public List<BillingDTO> getBillsByPatient(String email){
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
    
}
