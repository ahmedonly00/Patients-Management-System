package com.patients.patientsMgt.services;

import java.util.*;
import java.util.Optional;
import java.util.stream.Collectors;

import com.patients.patientsMgt.model.Appointments;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.repository.AppointmentsRepository;
import com.patients.patientsMgt.repository.DoctorsRepository;
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
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;


    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    public void createPrescription(Long appointmentId, PrescriptionDTO dto, String doctorUsername) {
        Appointments appointments = appointmentsRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment Not Found"));

        Doctors doctors = doctorsRepository.findByFullName(doctorUsername)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointments);
        prescription.setDoctor(doctors);
        prescription.setMedication(dto.getMedication());
        prescription.setDosage(dto.getDosage());
        prescription.setInstructions(dto.getInstructions());
        prescription.setPrescriptionDate(dto.getPrescriptionDate());
        prescription.setStatus(dto.getStatus());

        prescriptionRepository.save(prescription);
    }

    public Prescription updatePrescription(Long prescriptionId, PrescriptionDTO dto, String doctorUsername) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));

        Doctors doctors = doctorsRepository.findByFullName(doctorUsername)
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
        return prescriptionRepository.findByPatientUserEmail(email)
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