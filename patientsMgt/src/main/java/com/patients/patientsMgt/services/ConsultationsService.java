package com.patients.patientsMgt.services;

import com.patients.patientsMgt.dto.PatientsDTO;
import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.exceptions.customExceptions.UnauthorizedException;
import com.patients.patientsMgt.model.Appointments;
import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.repository.AppointmentsRepository;
import com.patients.patientsMgt.repository.ConsultationsRepository;

import com.patients.patientsMgt.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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
    private PatientsRepository patientsRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;


    public Page<Consultations> getAllConsultations(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return consultationsRepository.findAll(pageable);
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
        Patients patients = patientsRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Email", email));

        return consultationsRepository.findByPatient(patients)
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
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Email", email));

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
            .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Email", email));

        return consultationsRepository.findByDoctor(doctors)
                .stream()
                .map(Consultations::getPatient)
                .filter(Objects::nonNull)
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
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", "AppointmentId", appointmentId));

        Doctors doctors = doctorsRepository.findByEmail(doctorUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Email", doctorUsername));

        Consultations consultation = new Consultations();
        consultation.setAppointment(appointments);
        consultation.setDoctor(doctors);
        consultation.setPatient(appointments.getPatient());
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
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "ConsultationId", consultationId));

        Doctors doctors = doctorsRepository.findByFullName(doctorUsername)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "FullName", doctorUsername));

        if (!consultation.getDoctor().getDoctorId().equals(doctors.getDoctorId())) {
            throw new UnauthorizedException("You are not authorized to update this prescription");
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