package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.dto.LabTestDTO;
import com.patients.patientsMgt.model.LabTest;
import com.patients.patientsMgt.model.Medication;
import com.patients.patientsMgt.services.ConsultationsService;
import com.patients.patientsMgt.services.LabTestService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/labTest")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllLabTest(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<LabTest> labTestPage = labTestService.getAllLabTest(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", labTestPage.getContent());
        response.put("CurrentPage", labTestPage.getNumber());
        response.put("TotalItems", labTestPage.getTotalElements());
        response.put("TotalPages", labTestPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

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
