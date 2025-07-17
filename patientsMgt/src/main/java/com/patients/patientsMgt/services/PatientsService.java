package com.patients.patientsMgt.services;

import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;

@Service
public class PatientsService {
    @Autowired
    private PatientsRepository patientRepository;

    public List<Patients> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patients> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patients savePatient(Patients patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
} 