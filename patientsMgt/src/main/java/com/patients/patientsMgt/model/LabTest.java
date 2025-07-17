package com.patients.patientsMgt.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lab_tests")
public class LabTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labTestId;
    private String testName;
    private LocalDate testDate;
    private String result;

    public LabTest() {
    }

    public LabTest(Long labTestId, String testName, LocalDate testDate, String result) {
        this.labTestId = labTestId;
        this.testName = testName;
        this.testDate = testDate;
        this.result = result;
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
    
}
