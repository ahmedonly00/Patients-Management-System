package com.patients.patientsMgt.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name", nullable = false)
    private DepartmentName departmentName;

    @Column(name = "is_active", nullable = false)
    private boolean is_active;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Doctors> doctors;

    public Department() {
    }

    public Department(Long departmentId, DepartmentName departmentName, boolean is_active, List<Doctors> doctors) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.is_active = is_active;
        this.doctors = doctors;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public DepartmentName getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(DepartmentName departmentName) {
        this.departmentName = departmentName;
    }

    public List<Doctors> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctors> doctors) {
        this.doctors = doctors;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }   

    
      public enum DepartmentName {
        CARDIOLOGY,
        NEUROLOGY,
        PEDIATRICS
    }

}

