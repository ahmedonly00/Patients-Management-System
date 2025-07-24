package com.patients.patientsMgt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.repository.PatientsRepository;

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

    public Patients findByUser(Users user) {
        return patientRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Patient not found for user: " + user.getUserName()));
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
} 