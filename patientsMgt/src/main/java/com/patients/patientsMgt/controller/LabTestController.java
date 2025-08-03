package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.dto.LabTestDTO;
import com.patients.patientsMgt.services.LabTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/labTest")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @GetMapping(value = "/getLabResults")
    public ResponseEntity<List<LabTestDTO>> getLabResults (Principal principal){
        String email = principal.getName();
        return ResponseEntity.ok(labTestService.getLabResults(email));
    }
}
