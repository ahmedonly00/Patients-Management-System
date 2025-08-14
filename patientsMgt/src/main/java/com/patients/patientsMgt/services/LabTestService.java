package com.patients.patientsMgt.services;

import java.util.List;
import java.util.stream.Collectors;

import com.patients.patientsMgt.dto.PrescriptionDTO;
import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.LabTest;
import com.patients.patientsMgt.model.Prescription;
import com.patients.patientsMgt.repository.ConsultationsRepository;
import com.patients.patientsMgt.repository.DoctorsRepository;
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
                .orElseThrow(() -> new RuntimeException("Doctor Not Found"));

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
                .orElseThrow(() -> new RuntimeException("Consultation Not Found"));

        Doctors doctors = doctorsRepository.findByEmail(doctorEmail)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        LabTest labTest = new LabTest();
        labTest.setConsultation(consultations);
        labTest.setDoctor(doctors);
        labTest.setTestType(dto.getTestType());
        labTest.setTestName(dto.getTestName());
        labTest.setTestDate(dto.getTestDate());
        labTest.setSampleDate(dto.getSampleDate());
        labTest.setTestStatus(dto.getTestStatus());
        labTest.setComments(dto.getComments());

        return labTestRepository.save(labTest);
    }
    
}
