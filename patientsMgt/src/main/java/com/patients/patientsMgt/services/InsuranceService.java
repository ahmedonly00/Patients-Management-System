package com.patients.patientsMgt.services;

import com.patients.patientsMgt.dto.InsuranceDTO;
import com.patients.patientsMgt.model.Insurance;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.repository.InsuranceRepository;

import com.patients.patientsMgt.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }

    public Optional<Insurance> getInsuranceById(Long id) {
        return insuranceRepository.findById(id);
    }

    public Insurance saveInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    public void deleteInsurance(Long id) {
        insuranceRepository.deleteById(id);
    }

    public List<InsuranceDTO> getInsuranceByPatient(String email){
        Patients patients = patientsRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient Not Found"));

        return insuranceRepository.findByPatient(patients)
            .stream()
            .map(p -> new InsuranceDTO(
                p.getInsuranceId(), 
                p.getProviderName(), 
                p.getPolicyNumber(), 
                p.getCoverageDetails(), 
                p.getCoverage_amount(), 
                p.isIs_active(),
                p.getExpiry_date()))
            .collect(Collectors.toList());
    }


    
} 