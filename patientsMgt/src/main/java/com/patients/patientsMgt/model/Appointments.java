package com.patients.patientsMgt.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private AppointmentType appointmentType;
    private Status status;
    private String notes;
    private boolean sentReminder;
    private LocalDate created_at;
    private LocalDate updated_at;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctors doctor;

    public Appointments() {}
    

    public Appointments(Long appointmentId, String appointmentDate, String appointmentTime,
            AppointmentType appointmentType, Status status, String notes, boolean sentReminder, LocalDate created_at,
            LocalDate updated_at, Patients patient, Doctors doctor) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentType = appointmentType;
        this.status = status;
        this.notes = notes;
        this.sentReminder = sentReminder;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.patient = patient;
        this.doctor = doctor;
    }


    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isSentReminder() {
        return sentReminder;
    }

    public void setSentReminder(boolean sentReminder) {
        this.sentReminder = sentReminder;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }


    public enum Status {
        SCHEDULED,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    public enum AppointmentType {
        CONSULTATION,
        FOLLOWUP,
        EMERGENCY
        
    }
}
