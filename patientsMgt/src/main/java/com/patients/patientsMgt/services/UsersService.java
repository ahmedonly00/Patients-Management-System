package com.patients.patientsMgt.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.dto.UsersDTO;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Patients.Gender;
import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.model.Users.Role;
import com.patients.patientsMgt.repository.DoctorsRepository;
import com.patients.patientsMgt.repository.PatientsRepository;
import com.patients.patientsMgt.repository.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PatientsRepository patientRepository;
    
    @Autowired
    private DoctorsRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users findByUsername(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + email));
    }

    // public Users saveUser(UsersDTO user) {
    //     if(!usersRepository.findByUserName(user.getUserName()).isEmpty()) {
    //         throw new RuntimeException("Username already exists: " + user.getUserName());
    //     }
        
    //     Users newUser = new Users();
    //     newUser.setUserName(user.getUserName());
    //     newUser.setEmail(user.getEmail());
    //     newUser.setPassword(passwordEncoder.encode(user.getPassword()));
    //     newUser.setRole(Role.valueOf(user.getRole()));
    //     newUser.setIsActive(true);
    //     return usersRepository.save(newUser);
    // }

    public Users saveUser(UsersDTO dto) {
        if (usersRepository.existsByUserName(dto.getUserName())) {
            throw new RuntimeException("Username already taken");
        }

        Users user = new Users();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.valueOf(dto.getRole()));
        user.setIsActive(true);
        Users savedUser = usersRepository.save(user);

        Role userRole = Role.valueOf(dto.getRole());

        if (userRole.equals(Role.PATIENT)) {
            Patients patient = new Patients();
            patient.setFullName(dto.getFullName());
            patient.setPhoneNumber(dto.getPhoneNumber());
            patient.setEmail(dto.getEmail());
            patient.setAddress(dto.getAddress());
            patient.setEmergencyContact(dto.getEmergencyContact());
            patient.setGender(Gender.valueOf(dto.getGender()));
            patient.setDate_of_birth(dto.getDate_of_birth());
            patient.setUser(user);
            patientRepository.save(patient);
        } else if (userRole.equals(Role.DOCTOR)) {
            Doctors doctor = new Doctors();
            doctor.setFullName(dto.getFullName());
            doctor.setPhoneNumber(dto.getPhoneNumber());
            doctor.setEmail(dto.getEmail());
            doctor.setSpecialty(dto.getSpecialty());
            doctor.setUser(user);
            doctorRepository.save(doctor);
        }

        return savedUser;
    }

    public Users updateUser(Long id, Users userDetails) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUserName(userDetails.getUserName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());

            return usersRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
} 