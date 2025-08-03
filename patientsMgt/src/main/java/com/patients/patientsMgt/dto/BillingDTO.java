package com.patients.patientsMgt.dto;

import com.patients.patientsMgt.model.Billing;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class BillingDTO {

    private Long billingId;

    private double consultationFee;

    private double medicationCost;

    private double labTestFee;

    private double totalAmount;

    private double insuranceCoverage;

    private double patientPayableAmount;

    private Billing.BillingStatus billingStatus;

    private LocalDate dueDate;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public BillingDTO() {
    }

    public BillingDTO(Long billingId, double consultationFee, double medicationCost, double labTestFee, double totalAmount, double insuranceCoverage, double patientPayableAmount, Billing.BillingStatus billingStatus, LocalDate dueDate, LocalDate createdAt, LocalDate updatedAt) {
        this.billingId = billingId;
        this.consultationFee = consultationFee;
        this.medicationCost = medicationCost;
        this.labTestFee = labTestFee;
        this.totalAmount = totalAmount;
        this.insuranceCoverage = insuranceCoverage;
        this.patientPayableAmount = patientPayableAmount;
        this.billingStatus = billingStatus;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public double getMedicationCost() {
        return medicationCost;
    }

    public void setMedicationCost(double medicationCost) {
        this.medicationCost = medicationCost;
    }

    public double getLabTestFee() {
        return labTestFee;
    }

    public void setLabTestFee(double labTestFee) {
        this.labTestFee = labTestFee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(double insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }

    public double getPatientPayableAmount() {
        return patientPayableAmount;
    }

    public void setPatientPayableAmount(double patientPayableAmount) {
        this.patientPayableAmount = patientPayableAmount;
    }

    public Billing.BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(Billing.BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
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
