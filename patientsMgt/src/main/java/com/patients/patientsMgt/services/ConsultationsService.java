package com.patients.patientsMgt.services;

import com.patients.patientsMgt.dto.PatientsDTO;
import com.patients.patientsMgt.model.Appointments;
import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.repository.AppointmentsRepository;
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

    @Autowired
    private AppointmentsRepository appointmentsRepository;


    public List<Consultations> getAllConsultations() {
        return consultationsRepository.findAll();
    }

    public Optional<Consultations> getConsultationById(Long id) {
        return consultationsRepository.findById(id);
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
                .map(p -> new PatientsDTO(
                        p.getPatientId(),
                        p.getFullName(), 
                        p.getDate_of_birth(), 
                        p.getGender(),
                        p.getPhoneNumber(), 
                        p.getEmail(),
                        p.getAddress(),
                        p.getEmergencyContact()))
                .collect(Collectors.toList());

    }

    public Consultations createConsultation(Long appointmentId, ConsultationsDTO dto, String doctorUsername) {
        Appointments appointments = appointmentsRepository.findByAppointmentId(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment Not Found"));

        Doctors doctors = doctorsRepository.findByFullName(doctorUsername)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Consultations consultation = new Consultations();
        consultation.setAppointment(appointments);
        consultation.setDoctor(doctors);
        consultation.setConsultationDate(dto.getConsultationDate());
        consultation.setConsultationType(dto.getConsultationType());
        consultation.setHistory_of_illness(dto.getHistory_of_illness());
        consultation.setPhysicalExamination(dto.getPhysicalExamination());
        consultation.setTreatmentPlan(dto.getTreatmentPlan());
        consultation.setNotes(dto.getNotes());
        consultation.setDiagnosis(dto.getDiagnosis());
        consultation.setStatus(dto.getStatus());

        return consultationsRepository.save(consultation);
    }

    public Consultations updateConsultation(Long consultationId, ConsultationsDTO dto, String doctorUsername) {
        Consultations consultation = consultationsRepository.findByConsultationId(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));

        Doctors doctors = doctorsRepository.findByFullName(doctorUsername)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!consultation.getDoctor().getDoctorId().equals(doctors.getDoctorId())) {
            throw new RuntimeException("You are not authorized to update this prescription");
        }

        consultation.setConsultationType(dto.getConsultationType());
        consultation.setConsultationType(dto.getConsultationType());
        consultation.setHistory_of_illness(dto.getHistory_of_illness());
        consultation.setPhysicalExamination(dto.getPhysicalExamination());
        consultation.setTreatmentPlan(dto.getTreatmentPlan());
        consultation.setNotes(dto.getNotes());
        consultation.setDiagnosis(dto.getDiagnosis());
        consultation.setStatus(dto.getStatus());

        return consultationsRepository.save(consultation);
    }
}