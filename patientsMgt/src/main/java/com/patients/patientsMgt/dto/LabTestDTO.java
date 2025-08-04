package com.patients.patientsMgt.dto;

import com.patients.patientsMgt.model.LabTest;

import java.time.LocalDate;

public class LabTestDTO {

    private Long labTestId;

    private String testType;

    private String testName;

    private LocalDate testDate;

    private LocalDate sampleDate;

    private String result;

    private LabTest.TestStatus testStatus;

    private String comments;

    public LabTestDTO() {
    }

    public LabTestDTO(Long labTestId, String testType, String testName, LocalDate testDate, LocalDate sampleDate, String result, LabTest.TestStatus testStatus, String comments) {
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

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
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

    public LocalDate getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(LocalDate sampleDate) {
        this.sampleDate = sampleDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LabTest.TestStatus getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(LabTest.TestStatus testStatus) {
        this.testStatus = testStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
