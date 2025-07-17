package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
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

    @PostMapping(value = "/createUser")
    public Users createUser(@RequestBody Users user) {
        return usersService.saveUser(user);
    }

    @PutMapping(value = "/updateUser/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        user.setUserId(id);
        return usersService.saveUser(user);
    }

    @DeleteMapping(value = "/deleteUser/{id}")     
    public void deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
    }
} 