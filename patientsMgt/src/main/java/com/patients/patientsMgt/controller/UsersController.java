package com.patients.patientsMgt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.patients.patientsMgt.dto.DoctorsDTO;
import com.patients.patientsMgt.dto.PatientsDTO;
import com.patients.patientsMgt.dto.UsersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.services.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Page<Users> usersPage = usersService.getAllUsers(page, size, sortBy, direction);

        Map<String, Object> response = new HashMap<>();
        response.put("Users", usersPage.getContent());
        response.put("CurrentPage", usersPage.getNumber());
        response.put("TotalItems", usersPage.getTotalElements());
        response.put("TotalPages", usersPage.getTotalPages());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "getUserById/{id}")
    public Optional<Users> getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PostMapping(value = "/registerUser")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UsersDTO dto) {
        String savedUser = usersService.registerUser(dto);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @Valid @RequestBody Users userDetails) {
        Users updatedUser = usersService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/deleteUser/{id}")     
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }
}


