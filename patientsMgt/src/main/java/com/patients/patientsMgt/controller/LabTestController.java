package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.dto.ConsultationsDTO;
import com.patients.patientsMgt.dto.LabTestDTO;
import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.services.ConsultationsService;
import com.patients.patientsMgt.services.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/labTest")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @Autowired
    private ConsultationsService consultationsService;

    @GetMapping(value = "/getLabResults")
    public ResponseEntity<List<LabTestDTO>> getLabResults (Principal principal){
        String email = principal.getName();
        return ResponseEntity.ok(labTestService.getLabResults(email));
    }

    @PostMapping(value = "/createLabTest")
    public ResponseEntity<Consultations> createConsultation(
            @PathVariable Long appointmentId,
            @RequestBody ConsultationsDTO dto,
            Principal principal ) {

        String doctorUsername = principal.getName();
        Consultations createConsultation = consultationsService.createConsultation(appointmentId, dto, doctorUsername);
        return ResponseEntity.ok(createConsultation);
    }

}
