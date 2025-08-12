package com.patients.patientsMgt.services;

import java.util.List;
import java.util.Optional;

import com.patients.patientsMgt.dto.DoctorsDTO;
import com.patients.patientsMgt.dto.PatientsDTO;
import com.patients.patientsMgt.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.model.Patients.Gender;
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

    public String registerPatient(PatientsDTO dto) {
        if (patientRepository.findByUserEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Patient already exist");
        }

        Patients patients = new Patients();
        patients.setFullName(dto.getFullName());
        patients.setDate_of_birth(dto.getDate_of_birth());
        patients.setGender(dto.getGender());
        patients.setPhoneNumber(dto.getPhoneNumber());
        patients.setEmail(dto.getEmail());
        patients.setAddress(dto.getAddress());
        patients.setEmergencyContact(dto.getEmergencyContact());
        patientRepository.save(patients);

        return "Patients Registered Successfully!";
    }

    public String registerDoctor(DoctorsDTO dto){
        if ((doctorRepository.findByEmail(dto.getEmail())).isPresent()){
            throw new RuntimeException("User Already exists!");
        }

        Doctors doctor = new Doctors();
        doctor.setFullName(dto.getFullName());
        doctor.setPhoneNumber(dto.getPhoneNumber());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialty(dto.getSpecialty());
        doctorRepository.save(doctor);

        return "Doctor Registered Successfully!";

    }

    public Users updateUser(Long id, Users userDetails) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
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