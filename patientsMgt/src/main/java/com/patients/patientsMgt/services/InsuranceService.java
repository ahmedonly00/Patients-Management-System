package com.patients.patientsMgt.services;

import com.patients.patientsMgt.model.Insurance;
import com.patients.patientsMgt.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;

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
} 