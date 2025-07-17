package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.Department;
import com.patients.patientsMgt.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping(value = "getDepartmentById/{id}")
    public Optional<Department> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping(value = "/createDepartment")
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.saveDepartment(department);
    }

    @PutMapping(value = "/updateDepartment/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        department.setDepartmentId(id);
        return departmentService.saveDepartment(department);
    }

    @DeleteMapping(value = "/deleteDepartment/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
} 