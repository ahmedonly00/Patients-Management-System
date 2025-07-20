package com.patients.patientsMgt.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prescription_details")
public class PrescriptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_detail_id")
    private Long prescriptionDetailId;

    @Column(name = "dosage", nullable = false)
    private String dosage;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
    
    @Column(name = "instructions", nullable = false)
    private String instructions;


    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    public PrescriptionDetail() {}

    public PrescriptionDetail(Long prescriptionDetailId, Prescription prescription, Medication medication, String dosage, String paymentStatus, LocalDate paymentDate, String instructions) {
        this.prescriptionDetailId = prescriptionDetailId;
        this.prescription = prescription;
        this.medication = medication;
        this.dosage = dosage;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.instructions = instructions;
    }

    public Long getPrescriptionDetailId() {
        return prescriptionDetailId;
    }

    public void setPrescriptionDetailId(Long prescriptionDetailId) {
        this.prescriptionDetailId = prescriptionDetailId;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
