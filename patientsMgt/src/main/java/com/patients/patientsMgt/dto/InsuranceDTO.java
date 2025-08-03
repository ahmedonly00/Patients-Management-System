package com.patients.patientsMgt.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class InsuranceDTO {

    private Long insuranceId;

    private String providerName;

    private String policyNumber;

    private String coverageDetails;

    private float  coverage_amount;

    private boolean is_active;

    private LocalDate expiry_date;

    public InsuranceDTO() {
    }

    public InsuranceDTO(Long insuranceId, String providerName, String policyNumber, String coverageDetails, float coverage_amount, boolean is_active, LocalDate expiry_date) {
        this.insuranceId = insuranceId;
        this.providerName = providerName;
        this.policyNumber = policyNumber;
        this.coverageDetails = coverageDetails;
        this.coverage_amount = coverage_amount;
        this.is_active = is_active;
        this.expiry_date = expiry_date;
    }

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
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
