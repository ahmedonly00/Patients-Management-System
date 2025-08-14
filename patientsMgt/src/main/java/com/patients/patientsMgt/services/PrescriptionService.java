package com.patients.patientsMgt.services;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.repository.ConsultationsRepository;
import com.patients.patientsMgt.repository.DoctorsRepository;
import com.patients.patientsMgt.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.model.Prescription;
import com.patients.patientsMgt.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private ConsultationsRepository consultationsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;


    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    public Prescription createPrescription(Long consultationId, PrescriptionDTO dto, String doctorUsername) {
        Consultations consultations = consultationsRepository.findByConsultationId(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation Not Found"));

        Doctors doctors = doctorsRepository.findByEmail(doctorUsername)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Prescription prescription = new Prescription();
        prescription.setConsultation(consultations);
        prescription.setDoctor(doctors);
        prescription.setMedication(dto.getMedication());
        prescription.setDosage(dto.getDosage());
        prescription.setInstructions(dto.getInstructions());
        prescription.setPrescriptionDate(dto.getPrescriptionDate());
        prescription.setStatus(dto.getStatus());

        return prescriptionRepository.save(prescription);
    }

    public Prescription updatePrescription(Long prescriptionId, PrescriptionDTO dto, String doctorUsername) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        Doctors doctors = doctorsRepository.findByEmail(doctorUsername)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!prescription.getDoctor().getDoctorId().equals(doctors.getDoctorId())) {
            throw new RuntimeException("You are not authorized to update this prescription");
        }

        prescription.setMedication(dto.getMedication());
        prescription.setDosage(dto.getDosage());
        prescription.setInstructions(dto.getInstructions());
        prescription.setPrescriptionDate(dto.getPrescriptionDate());
        prescription.setStatus(dto.getStatus());

        return prescriptionRepository.save(prescription);
    }


    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }

    
    public List<PrescriptionDTO> getPrescriptionsByPatient(String email) {
        Patients patients = patientsRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient Not Found"));

        return prescriptionRepository.findByPatient(patients)
            .stream()
            .map(p -> new PrescriptionDTO(
                p.getPrescriptionId(),
                p.getMedication(), 
                p.getDosage(), 
                p.getInstructions(), 
                p.getPrescriptionDate(),
                p.getStatus()))
            .collect(Collectors.toList());
    }

} 