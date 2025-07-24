package com.patients.patientsMgt.dto;

public class AssignDoctorDTO {
    private Long doctorId;

    public AssignDoctorDTO() {}

    public AssignDoctorDTO(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

}

