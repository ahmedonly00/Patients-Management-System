package com.patients.patientsMgt.services;

import com.patients.patientsMgt.dto.LabTestDTO;
import com.patients.patientsMgt.model.LabTest;
import com.patients.patientsMgt.repository.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

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
                .map(p -> new LabTestDTO(p.getLabTestId(), p.getTestType(), p.getTestName(), p.getTestDate(), p.getSampleDate(), p.getResult(), p.getTestStatus(), p.getComments()))
                .collect(Collectors.toList());

    }
    
}
