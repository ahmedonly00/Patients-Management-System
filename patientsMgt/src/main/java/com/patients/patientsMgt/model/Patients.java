package com.patients.patientsMgt.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
    private String fullName;
    private LocalDate date_of_birth;
    private Gender gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String emergencyContact;
    private LocalDate created_at;
    private LocalDate updated_at;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Appointments> appointments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Consultations> consultations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Insurance> insurances;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Patients() {
    }

    public Patients(Long patientId, String fullName, LocalDate date_of_birth, Gender gender, String phoneNumber,
            String email, String address, String emergencyContact, LocalDate created_at, LocalDate updated_at,
            List<Appointments> appointments, List<Consultations> consultations, List<Insurance> insurances,
            List<Payment> payments, List<Prescription> prescriptions, Users user) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.date_of_birth = date_of_birth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.emergencyContact = emergencyContact;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.appointments = appointments;
        this.consultations = consultations;
        this.insurances = insurances;
        this.payments = payments;
        this.prescriptions = prescriptions;
        this.user = user;
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

        public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public List<Appointments> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointments> appointments) {
        this.appointments = appointments;
    }

    public List<Consultations> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultations> consultations) {
        this.consultations = consultations;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    public enum Gender {
        MALE,
        FEMALE
    }
}
