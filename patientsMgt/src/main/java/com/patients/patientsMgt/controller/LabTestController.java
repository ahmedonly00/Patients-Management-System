package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.dto.LabTestDTO;
import com.patients.patientsMgt.model.LabTest;
import com.patients.patientsMgt.services.ConsultationsService;
import com.patients.patientsMgt.services.LabTestService;
import jakarta.validation.constraints.NotNull;
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

    @GetMapping(value = "/getLabResults")
    public ResponseEntity<List<LabTestDTO>> getLabResults (@NotNull Principal principal){
        String email = principal.getName();
        return ResponseEntity.ok(labTestService.getLabResults(email));
    }

    @PostMapping(value = "/{consultationId}/createLabTest")
    public ResponseEntity<LabTest> createLabTest(
            @PathVariable Long consultationId,
            @RequestBody LabTestDTO dto,
            @NotNull Principal principal ) {

        String doctorUsername = principal.getName();
        LabTest createLabTest = labTestService.createLabTest(consultationId, dto, doctorUsername);
        return ResponseEntity.ok(createLabTest);
    }

}
