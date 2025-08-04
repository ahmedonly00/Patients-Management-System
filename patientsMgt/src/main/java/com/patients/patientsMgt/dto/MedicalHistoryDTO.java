package com.patients.patientsMgt.dto;

import com.patients.patientsMgt.model.MedicalHistory;

import java.time.LocalDate;

public class MedicalHistoryDTO {

    private Long medicalHistoryId;

    private String medicalCondition;

    private String description;

    private LocalDate diagnosisDate;

    private MedicalHistory.Severity severity;

    private boolean isActive;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public MedicalHistoryDTO() {
    }

    public MedicalHistoryDTO(Long medicalHistoryId, String medicalCondition, String description, LocalDate diagnosisDate, MedicalHistory.Severity severity, boolean isActive, LocalDate createdAt, LocalDate updatedAt) {
        this.medicalHistoryId = medicalHistoryId;
        this.medicalCondition = medicalCondition;
        this.description = description;
        this.diagnosisDate = diagnosisDate;
        this.severity = severity;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(Long medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(LocalDate diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public MedicalHistory.Severity getSeverity() {
        return severity;
    }

    public void setSeverity(MedicalHistory.Severity severity) {
        this.severity = severity;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
}
