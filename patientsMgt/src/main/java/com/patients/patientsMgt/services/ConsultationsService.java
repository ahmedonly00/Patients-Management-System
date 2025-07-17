package com.patients.patientsMgt.services;

import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.repository.ConsultationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationsService {
    @Autowired
    private ConsultationsRepository consultationsRepository;

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
}