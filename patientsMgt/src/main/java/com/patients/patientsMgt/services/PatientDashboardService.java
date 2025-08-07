package com.patients.patientsMgt.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientDashboardService {
    
    @Autowired
    private PatientsService patientService;

    @Autowired
    private AppointmentsService appointmentService;

    @Autowired
    private ConsultationsService consultationService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private LabTestService labTestService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private InsuranceService insuranceService;

    public Map<String, Object> getDashboardData(String email) {
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("personalInfo", patientService.getPatientInfo(email));
        dashboard.put("upcomingAppointments", appointmentService.getUpcomingAppointments(email));
        dashboard.put("consultationHistory", consultationService.getConsultationHistory(email));
        dashboard.put("prescriptions", prescriptionService.getPrescriptionsByPatient(email));
        dashboard.put("labResults", labTestService.getLabResults(email));
        dashboard.put("billingInfo", billingService.getBillsByPatient(email));
        dashboard.put("insuranceInfo", insuranceService.getInsuranceByPatient(email));
        return dashboard;
    }
}
