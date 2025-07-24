package com.patients.patientsMgt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patients.patientsMgt.dto.UsersDTO;
import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.services.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/all")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping(value = "getUserById/{id}")
    public Optional<Users> getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    @PostMapping(value = "/registerUser")
    public ResponseEntity<Users> registerUser(@Valid @RequestBody UsersDTO user) {
        Users savedUser = usersService.saveUser(user);
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


