package com.patients.patientsMgt.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

public class DoctorDashboardService {
    
    @Autowired
    private DoctorsService doctorService;

    @Autowired
    private AppointmentsService appointmentService;

    @Autowired
    private ConsultationsService consultationService;

    @Autowired
    private LabTestService labTestService;

    public Map<String, Object> getDashboardData(String email) {
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("doctorInfo", doctorService.getDoctorInfo(email));
        dashboard.put("todaysAppointments", appointmentService.getTodaysAppointments(email));
        dashboard.put("patientList", consultationService.getPatientsByDoctor(email));
        dashboard.put("pendingLabResults", labTestService.getPendingLabResults(email));
        dashboard.put("consultationHistory", consultationService.getConsultationHistoryByDoctor(email));
        return dashboard;
    }
}
