package com.patients.patientsMgt.services;

import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Long id, Users userDetails) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUserName(userDetails.getUserName());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            return usersRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
} 