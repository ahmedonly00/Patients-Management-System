package com.patients.patientsMgt.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class PatientInsuranceDTO {

    private Long patientInsuranceId;

    private LocalDate startDate;

    private LocalDate endDate;

    public PatientInsuranceDTO() {
    }

    public PatientInsuranceDTO(Long patientInsuranceId, LocalDate startDate, LocalDate endDate) {
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
}
