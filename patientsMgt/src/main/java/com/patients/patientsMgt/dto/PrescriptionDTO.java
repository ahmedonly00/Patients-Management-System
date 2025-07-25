package com.patients.patientsMgt.dto;

import java.time.LocalDate;

import com.patients.patientsMgt.model.Prescription.Status;


public class PrescriptionDTO {

    private String medication;
    private String dosage;
    private String instructions;
    private LocalDate prescriptionDate;
    private Status status;

    public PrescriptionDTO() {
    }
    
    public PrescriptionDTO(String medication, String dosage, String instructions, LocalDate prescriptionDate,
            Status status) {
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.prescriptionDate = prescriptionDate;
        this.status = status;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDate getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(LocalDate prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    } 
    
}
