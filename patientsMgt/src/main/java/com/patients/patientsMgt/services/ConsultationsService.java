package com.patients.patientsMgt.services;

import com.patients.patientsMgt.dto.PatientsDTO;
import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.repository.ConsultationsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.patients.patientsMgt.dto.ConsultationsDTO;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.repository.DoctorsRepository;

@Service
public class ConsultationsService {
    @Autowired
    private ConsultationsRepository consultationsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;


    public List<Consultations> getAllConsultations() {
        return consultationsRepository.findAll();
    }

    public Optional<Consultations> getConsultationById(Long id) {
        return consultationsRepository.findById(id);
    }

    public Consultations saveConsultation(Consultations consultation) {
        return consultationsRepository.save(consultation);
    }

    public void deleteConsultation(Long id) {
        consultationsRepository.deleteById(id);
    }

    public String updateConsultationStatus(Long id, Consultations.Status status) {
        Optional<Consultations> consultationOpt = consultationsRepository.findById(id);
        if (consultationOpt.isPresent()) {
            Consultations consultation = consultationOpt.get();
            consultation.setStatus(status);
            consultationsRepository.save(consultation);
            return "Consultation status updated successfully.";
        } else {
            return "Consultation not found.";
        }
    }

    
    public List<ConsultationsDTO> getConsultationHistory(String email) {
        return consultationsRepository.findByPatientUserEmail(email)
                .stream()
                .map(p -> new ConsultationsDTO(
                    p.getConsultationId(),
                    p.getConsultationDate(),
                    p.getConsultationType(),
                    p.getHistory_of_illness(),
                    p.getPhysicalExamination(),
                    p.getTreatmentPlan(),
                    p.getNotes(),
                    p.getDiagnosis(),
                    p.getStatus()
                ))
                .collect(Collectors.toList());       
    }


    public List<ConsultationsDTO> getConsultationHistoryByDoctor(String email) {
        Doctors doctors = doctorsRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return consultationsRepository.findByDoctor(doctors)
                .stream()
                .map(p -> new ConsultationsDTO(
                    p.getConsultationId(),
                    p.getConsultationDate(),
                    p.getConsultationType(),
                    p.getHistory_of_illness(),
                    p.getPhysicalExamination(),
                    p.getTreatmentPlan(),
                    p.getNotes(),
                    p.getDiagnosis(),
                    p.getStatus()

                ))
                .collect(Collectors.toList());   

    }

    public List<PatientsDTO> getPatientsByDoctor(String email) {
        Doctors doctors = doctorsRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Doctor not Found"));

        return consultationsRepository.findByDoctor(doctors)
                .stream()
                .map(Consultations::getPatient)
                .distinct()
                .map(p -> new PatientsDTO(p.getPatientId(), 
                        p.getFullName(), 
                        p.getDate_of_birth(), 
                        p.getGender(),
                        p.getPhoneNumber(), 
                        p.getEmail(),
                        p.getAddress(),
                        p.getEmergencyContact()))
                .collect(Collectors.toList());

    }
}