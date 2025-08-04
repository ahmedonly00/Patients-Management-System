package com.patients.patientsMgt.dto;

import com.patients.patientsMgt.model.Consultations;

public class ConsultationsDTO {

    private Long consultationId;

    private String consultationDate;

    private Consultations.ConsultationType consultationType;

    private String history_of_illness;

    private String physicalExamination;

    private String treatmentPlan;

    private String notes;

    private String diagnosis;

    private Consultations.Status status;

    public ConsultationsDTO() {
    }

    public ConsultationsDTO(Long consultationId, String consultationDate, Consultations.ConsultationType consultationType, String history_of_illness, String physicalExamination, String treatmentPlan, String notes, String diagnosis, Consultations.Status status) {
        this.consultationId = consultationId;
        this.consultationDate = consultationDate;
        this.consultationType = consultationType;
        this.history_of_illness = history_of_illness;
        this.physicalExamination = physicalExamination;
        this.treatmentPlan = treatmentPlan;
        this.notes = notes;
        this.diagnosis = diagnosis;
        this.status = status;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public Consultations.ConsultationType getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(Consultations.ConsultationType consultationType) {
        this.consultationType = consultationType;
    }

    public String getHistory_of_illness() {
        return history_of_illness;
    }

    public void setHistory_of_illness(String history_of_illness) {
        this.history_of_illness = history_of_illness;
    }

    public String getPhysicalExamination() {
        return physicalExamination;
    }

    public void setPhysicalExamination(String physicalExamination) {
        this.physicalExamination = physicalExamination;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Consultations.Status getStatus() {
        return status;
    }

    public void setStatus(Consultations.Status status) {
        this.status = status;
    }
}
