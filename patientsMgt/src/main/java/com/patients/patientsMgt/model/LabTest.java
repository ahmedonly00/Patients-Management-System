package com.patients.patientsMgt.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lab_tests")
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lab_test_id")
    private Long labTestId;

    @Column(name = "test_type", nullable = false)
    private String testType;

    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(name = "test_date", nullable = false)
    private LocalDate testDate;

    @Column(name = "sample_date", nullable = false)
    private LocalDate sampleDate;

    @Column(name = "result", nullable = false)
    private String result;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_status", nullable = false)
    private TestStatus testStatus;

    @Column(name = "comments", nullable = true)
    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patients patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctors doctor;

    @ManyToOne
    @JoinColumn(name = "consultation_id")
    private Consultations consultation;
    

    public LabTest() {
    }

    public LabTest(Long labTestId, String testType, String testName, LocalDate testDate, LocalDate sampleDate, String result, TestStatus testStatus, String comments) {
        this.labTestId = labTestId;
        this.testType = testType;
        this.testName = testName;
        this.testDate = testDate;
        this.sampleDate = sampleDate;
        this.result = result;
        this.testStatus = testStatus;
        this.comments = comments;
    }

    public Long getLabTestId() {
        return labTestId;
    }

    public void setLabTestId(Long labTestId) {
        this.labTestId = labTestId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public LocalDate getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(LocalDate sampleDate) {
        this.sampleDate = sampleDate;
    }

    public TestStatus getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(TestStatus testStatus) {
        this.testStatus = testStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Consultations getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultations consultation) {
        this.consultation = consultation;
    }

    public Doctors getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctors doctor) {
        this.doctor = doctor;
    }

    public Patients getPatient() {
        return patient;
    }

    public void setPatient(Patients patient) {
        this.patient = patient;
    }

    public enum TestStatus {
        PENDING,
        COMPLETED,
        CANCELLED
    }
    
}
