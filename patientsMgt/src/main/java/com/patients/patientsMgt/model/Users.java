package com.patients.patientsMgt.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    @Column(name = "last_login", nullable = true)
    private LocalDate last_login;

    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
    
    @Column(name = "updated_at", nullable = false)
    private LocalDate updated_at;

    @OneToOne(mappedBy = "user")
    private Patients patient;

    @OneToOne(mappedBy = "user")
    private Doctors doctor;

    public Users() {}


    public Users(Long userId, String userName, String email, String password, Role role, boolean is_active,
            LocalDate last_login, LocalDate created_at, LocalDate updated_at, Patients patient, Doctors doctor) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.is_active = is_active;
        this.last_login = last_login;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.patient = patient;
        this.doctor = doctor;
    }
    

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public LocalDate getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDate last_login) {
        this.last_login = last_login;
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

    public enum Role{
        PATIENT,
        DOCTOR,
        ADMIN,
        NURSE,
        RECEPTIONIST
    }

}
