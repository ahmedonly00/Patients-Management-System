package com.patients.patientsMgt.services;

import com.patients.patientsMgt.dto.InsuranceDTO;
import com.patients.patientsMgt.dto.PatientInsuranceDTO;
import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.model.Insurance;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.repository.InsuranceRepository;

import com.patients.patientsMgt.repository.PatientInsuranceRepository;
import com.patients.patientsMgt.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InsuranceService {
    @Autowired
    private PatientInsuranceRepository patientInsuranceRepository;

    @Autowired
    private PatientsRepository patientsRepository;

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

    public List<PatientInsuranceDTO> getInsuranceByPatient(String email){
        Patients patients = patientsRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Email", email));

//        Insurance insurance = insuranceRepository.findByInsuranceId(insuranceId)
//                .orElseThrow(() -> new ResourceNotFoundException(("Insurance", "Id", insuranceId));

        return patientInsuranceRepository.findByPatient(patients)
            .stream()
            .map(p -> new PatientInsuranceDTO(
                p.getPatientInsuranceId(),
                p.getStartDate(),
                p.getEndDate()))
            .collect(Collectors.toList());
    }


    
} 