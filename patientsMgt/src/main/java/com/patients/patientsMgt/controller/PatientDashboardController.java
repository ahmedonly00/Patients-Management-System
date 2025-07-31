package com.patients.patientsMgt.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.services.*;

@RestController
@RequestMapping(value ="/api/patient/dashboard")
public class PatientDashboardController {

    @Autowired
    private PatientDashboardService dashboardService;

    @GetMapping
    public ResponseEntity<?> getDashboard(Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(dashboardService.getDashboardData(email));
    }

}
    
