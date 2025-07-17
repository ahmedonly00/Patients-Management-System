package com.patients.patientsMgt.model;

import jakarta.persistence.*;



@Entity
@Table(name = "consultations")
public class Consultations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultationId;
    private String consultationDate;
    private String notes;
    private String diagnosis;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctors doctor;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    public Consultations() {}

    public Consultations(Long consultationId, Patients patient, Doctors doctor, Department department, String consultationDate, String notes, String diagnosis, Status status) {
        this.consultationId = consultationId;
        this.patient = patient;
        this.doctor = doctor;
        this.department = department;
        this.consultationDate = consultationDate;
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

    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public Doctors getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public enum Status {
        PENDING,
        COMPLETED,
        CANCELLED
    }
}
