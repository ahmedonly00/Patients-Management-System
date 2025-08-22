package com.patients.patientsMgt.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.model.Department;
import com.patients.patientsMgt.services.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Department> departmentPage = departmentService.getAllDepartments(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", departmentPage.getContent());
        response.put("CurrentPage", departmentPage.getNumber());
        response.put("TotalItems", departmentPage.getTotalElements());
        response.put("TotalPages", departmentPage.getTotalPages());

        return ResponseEntity.ok(response);
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