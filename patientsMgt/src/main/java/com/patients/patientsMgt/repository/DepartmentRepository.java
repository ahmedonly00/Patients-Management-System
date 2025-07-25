package com.patients.patientsMgt.repository;

import java.util.List;

import com.patients.patientsMgt.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByDepartmentId(Long departmentId);
    List<Department> findByDepartmentName(Department.DepartmentName departmentName);
}