package com.patients.patientsMgt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patients.patientsMgt.model.Department;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Users;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UsersDTO {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Users.Role role;

    private String phoneNumber;

    // ---- Patient-specific fields ----
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Patients.Gender gender;

    private String address;

    private String emergencyContact;

    // ---- Doctor-specific fields ----
    private String specialty;

    private Long departmentId;

    // ---- Optional org fields for staff (nurse/receptionist/admin) ----
    private String staffDepartment;

    public UsersDTO() {
    }

    public UsersDTO(String fullName, String email, String password, Users.Role role, String phoneNumber, LocalDate dateOfBirth, Patients.Gender gender, String address, String emergencyContact, String specialty, Long departmentId, String staffDepartment) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.specialty = specialty;
        this.departmentId = departmentId;
        this.staffDepartment = staffDepartment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users.Role getRole() {
        return role;
    }

    public void setRole(Users.Role role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Patients.Gender getGender() {
        return gender;
    }

    public void setGender(Patients.Gender gender) {
        this.gender = gender;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getStaffDepartment() {
        return staffDepartment;
    }

    public void setStaffDepartment(String staffDepartment) {
        this.staffDepartment = staffDepartment;
    }

    // ----- Role-aware validation -----
    @AssertTrue(message = "For PATIENT: dateOfBirth, gender, phoneNumber, address and emergencyContact are required")
    public boolean isPatientFieldsValidWhenPatient() {
        if (role == null || role != Users.Role.PATIENT) return true;
        return dateOfBirth != null
                && gender != null
                && notBlank(phoneNumber)
                && notBlank(address)
                && notBlank(emergencyContact);
    }

    @AssertTrue(message = "For DOCTOR: specialty and phoneNumber are required")
    public boolean isDoctorFieldsValidWhenDoctor() {
        if (role == null || role != Users.Role.DOCTOR) return true;
        return notBlank(specialty) && notBlank(phoneNumber);
    }

    @AssertTrue(message = "For NURSE/RECEPTIONIST/ADMIN: phoneNumber is required")
    public boolean isStaffFieldsValidWhenStaff() {
        if (role == null) return true;
        switch (role) {
            case NURSE, RECEPTIONIST, ADMIN -> { return notBlank(phoneNumber); }
            default -> { return true; }
        }
    }

    private boolean notBlank(String s) {
        return s != null && !s.trim().isEmpty();
    }

}
