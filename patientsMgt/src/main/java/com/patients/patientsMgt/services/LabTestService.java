package com.patients.patientsMgt.services;

import java.util.List;
import java.util.stream.Collectors;

import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.model.*;
import com.patients.patientsMgt.repository.ConsultationsRepository;
import com.patients.patientsMgt.repository.DoctorsRepository;
import com.patients.patientsMgt.repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.dto.LabTestDTO;
import com.patients.patientsMgt.repository.LabTestRepository;

@Service
public class LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    @Autowired
    private ConsultationsRepository consultationsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

//    public List<LabTestDTO> getLabResults (String email){
//        List<LabTest> labTests = labTestRepository.findByPatientUserEmail(email);
//        List<LabTestDTO> dtoList = new ArrayList<>();
//        for(LabTest lab : labTests){
//            dtoList.add(new LabTestDTO(lab.getLabTestId(), lab.getTestType(), lab.getTestName(), lab.getTestDate(), lab.getSampleDate(),lab.getResult(),lab.getTestStatus(), lab.getComments()));
//        }
//        return dtoList;
//
//    }

    public List<LabTestDTO> getLabResults (String email){
        Patients patients = patientsRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Email", email));

        return labTestRepository.findByPatientUserEmail(email)
                .stream()
                .map(p -> new LabTestDTO(
                        p.getLabTestId(), 
                        p.getTestType(), 
                        p.getTestName(), 
                        p.getTestDate(), 
                        p.getSampleDate(), 
                        p.getResult(), 
                        p.getTestStatus(), 
                        p.getComments()))
                .collect(Collectors.toList());

    }

    public List<LabTestDTO> getPendingLabResults(String doctorEmail) {
        Doctors doctors = doctorsRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Email", doctorEmail));

    return labTestRepository.findPendingTestsByDoctorEmail(doctorEmail)
            .stream()
            .map(p -> new LabTestDTO(
                    p.getLabTestId(),
                    p.getTestType(),
                    p.getTestName(),
                    p.getTestDate(),
                    p.getSampleDate(),
                    p.getResult(),
                    p.getTestStatus(),
                    p.getComments()
            ))
            .collect(Collectors.toList());       
    }

    public LabTest createLabTest(Long consultationId, LabTestDTO dto, String doctorEmail) {
        Consultations consultations = consultationsRepository.findByConsultationId(consultationId)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation", "Consultation Id", consultationId));

        Doctors doctors = doctorsRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Email", doctorEmail));

        LabTest labTest = new LabTest();
        labTest.setConsultation(consultations);
        labTest.setDoctor(doctors);
        labTest.setPatient(consultations.getPatient());
        labTest.setTestType(dto.getTestType());
        labTest.setTestName(dto.getTestName());
        labTest.setTestDate(dto.getTestDate());
        labTest.setSampleDate(dto.getSampleDate());
        labTest.setResult(dto.getResult());
        labTest.setTestStatus(dto.getTestStatus());
        labTest.setComments(dto.getComments());

        return labTestRepository.save(labTest);
    }
    
}
