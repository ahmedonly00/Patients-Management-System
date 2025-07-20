package com.patients.patientsMgt.model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_id")
    private Long billingId;

    @Column(name = "consultation_fee", nullable = false)
    private double consultationFee;

    @Column(name = "medication_cost", nullable = false)
    private double medicationCost;

    @Column(name = "lab_test_fee", nullable = false)
    private double labTestFee;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "insurance_coverage", nullable = false)
    private double insuranceCoverage;

    @Column(name = "patient_payable_amount", nullable = false)
    private double patientPayableAmount;

    @Column(name = "billing_status", nullable = false)
    private BillingStatus billingStatus;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultations consultation;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    public Billing() {} 

    public Billing(Long billingId, double consultationFee, double medicationCost, double labTestFee, double totalAmount,
            double insuranceCoverage, double patientPayableAmount, BillingStatus billingStatus, LocalDate dueDate,
            LocalDate createdAt, LocalDate updatedAt, Consultations consultation, Patients patient) {
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
        this.consultation = consultation;
        this.patient = patient;
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


    public BillingStatus getBillingStatus() {
        return billingStatus;
    }


    public void setBillingStatus(BillingStatus billingStatus) {
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


    public Consultations getConsultation() {
        return consultation;
    }


    public void setConsultation(Consultations consultation) {
        this.consultation = consultation;
    }


    public Patients getPatient() {
        return patient;
    }


    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    
    public enum BillingStatus {
        GENERATED,
        SENT,
        PAID,
        OVERDUE
    }
    
}
