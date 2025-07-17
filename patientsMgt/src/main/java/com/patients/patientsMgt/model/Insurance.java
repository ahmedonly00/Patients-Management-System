package com.patients.patientsMgt.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceId;
    private String providerName;
    private String policyNumber;
    private String coverageDetails;
    private float  coverage_amount;
    private boolean is_active;
    private LocalDate expiry_date;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    public Insurance() {}


    public Insurance(Long insuranceId, String providerName, String policyNumber, String coverageDetails,
            float coverage_amount, boolean is_active, LocalDate expiry_date, Patients patient) {
        this.insuranceId = insuranceId;
        this.providerName = providerName;
        this.policyNumber = policyNumber;
        this.coverageDetails = coverageDetails;
        this.coverage_amount = coverage_amount;
        this.is_active = is_active;
        this.expiry_date = expiry_date;
        this.patient = patient;
    }


    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getCoverageDetails() {
        return coverageDetails;
    }

    public void setCoverageDetails(String coverageDetails) {
        this.coverageDetails = coverageDetails;
    }

    public float getCoverage_amount() {
        return coverage_amount;
    }

    public void setCoverage_amount(float coverage_amount) {
        this.coverage_amount = coverage_amount;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDate getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }

}
