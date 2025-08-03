package com.patients.patientsMgt.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class DoctorsDTO {

    private Long doctorId;

    private String fullName;

    private String specialty;

    private String email;

    private String phoneNumber;

    public DoctorsDTO() {
    }

    public DoctorsDTO(Long doctorId, String fullName, String specialty, String email, String phoneNumber) {
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.specialty = specialty;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
