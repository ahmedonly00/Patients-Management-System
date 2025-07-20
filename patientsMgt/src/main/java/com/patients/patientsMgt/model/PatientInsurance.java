package com.patients.patientsMgt.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "patient_insurance")
public class PatientInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_insurance_id")
    private Long patientInsuranceId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)    
    private Insurance insurance;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patients patient;

    public PatientInsurance() {
    }

    public PatientInsurance(Long patientInsuranceId, LocalDate startDate, LocalDate endDate) {
        this.patientInsuranceId = patientInsuranceId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getPatientInsuranceId() {
        return patientInsuranceId;
    }

    public void setPatientInsuranceId(Long patientInsuranceId) {
        this.patientInsuranceId = patientInsuranceId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }
}
