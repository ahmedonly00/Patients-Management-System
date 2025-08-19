package com.patients.patientsMgt.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long insuranceId;

    @Column(name = "provider_name")
    private String providerName;

    @Column(name = "policy_number")
    private String policyNumber;

    @Column(name = "coverage_details")
    private String coverageDetails;

    @Column(name = "coverage_amount")
    private float  coverage_amount;

    @Column(name = "is_active")
    private boolean is_active;

    @Column(name = "expiry_date")
    private LocalDate expiry_date;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL)
    private List<PatientInsurance> patientInsurances;

    public Insurance() {}


    public Insurance(Long insuranceId, String providerName, String policyNumber, String coverageDetails,
            float coverage_amount, boolean is_active, LocalDate expiry_date) {
        this.insuranceId = insuranceId;
        this.providerName = providerName;
        this.policyNumber = policyNumber;
        this.coverageDetails = coverageDetails;
        this.coverage_amount = coverage_amount;
        this.is_active = is_active;
        this.expiry_date = expiry_date;
        //this.patient = patient;
    }


    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

//    public Patients getPatient() {
//        return patient;
//    }
//
//    public void setPatient(Patients patient) {
//        this.patient = patient;
//    }

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

    public List<PatientInsurance> getPatientInsurances() {
        return patientInsurances;
    }

    public void setPatientInsurances(List<PatientInsurance> patientInsurances) {
        this.patientInsurances = patientInsurances;
    }

}
