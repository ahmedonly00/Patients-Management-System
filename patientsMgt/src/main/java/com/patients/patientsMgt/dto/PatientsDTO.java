package com.patients.patientsMgt.dto;

import java.time.LocalDate;

import com.patients.patientsMgt.model.Patients.Gender;


public class PatientsDTO {
    
    private Long patientId;

    private String fullName;

    private LocalDate date_of_birth;

    private Gender gender;

    private String phoneNumber;

    private String email;

    private String address;

    private String emergencyContact;

    public PatientsDTO(Long patientId, String fullName, LocalDate date_of_birth, Gender gender, String phoneNumber,
            String email, String address, String emergencyContact) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.emergencyContact = emergencyContact;
    }

    public PatientsDTO() {
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    } 

}
