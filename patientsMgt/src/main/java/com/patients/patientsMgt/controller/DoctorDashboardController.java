package com.patients.patientsMgt.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patients.patientsMgt.services.DoctorDashboardService;

@RestController
@RequestMapping("/api/doctor/dashboard")
public class DoctorDashboardController {

    @Autowired
    private DoctorDashboardService dashboardService;

    @GetMapping
    public ResponseEntity<?> getDashboard(Principal principal) {
        String email = principal.getName();
        return ResponseEntity.ok(dashboardService.getDashboardData(email));
    }
    
}
