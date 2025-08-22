package com.patients.patientsMgt.services;

import java.util.List;
import java.util.Optional;

import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.dto.PatientsDTO;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.repository.PatientsRepository;

@Service
public class PatientsService {
    @Autowired
    private PatientsRepository patientRepository;

    public Page<Patients> getAllPatients(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return patientRepository.findAll(pageable);
    }

    public Optional<Patients> getPatientById(Long id) {

        return patientRepository.findById(id);
    }

    public Patients findByUser(Users user) {
        return patientRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Email", user.getEmail()));
    }

    public Patients savePatient(Patients patient) {

        return patientRepository.save(patient);
    }

    public void updatePatient(Patients patient) {

        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {

        patientRepository.deleteById(id);
    }

    public PatientsDTO getPatientInfo(String email) {
        Patients patients = patientRepository.findByUserEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Patient", "Email", email));

        return new PatientsDTO(
            patients.getPatientId(),
            patients.getFullName(),
            patients.getDate_of_birth(),
            patients.getGender(),
            patients.getPhoneNumber(),
            patients.getEmail(),
            patients.getAddress(),
            patients.getEmergencyContact()
        );

    }
} 