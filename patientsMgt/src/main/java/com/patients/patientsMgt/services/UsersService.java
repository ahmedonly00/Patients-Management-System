package com.patients.patientsMgt.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.patients.patientsMgt.dto.DoctorsDTO;
import com.patients.patientsMgt.dto.PatientsDTO;
import com.patients.patientsMgt.dto.UsersDTO;
import com.patients.patientsMgt.model.Department;
import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Patients;
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
    private DepartmentRepository departmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users findByUser(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + email));
    }

    public String registerUser(UsersDTO dto) {
        Users user = new Users();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setIsActive(true);
        user.setLastLogin(LocalDate.now());
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        usersRepository.save(user);

        if (dto.getRole() == Users.Role.PATIENT){

            Patients patients = new Patients();
            patients.setFullName(dto.getFullName());
            patients.setDate_of_birth(dto.getDateOfBirth());
            patients.setGender(dto.getGender());
            patients.setPhoneNumber(dto.getPhoneNumber());
            patients.setEmail(dto.getEmail());
            patients.setAddress(dto.getAddress());
            patients.setEmergencyContact(dto.getEmergencyContact());
            patients.setCreated_at(LocalDate.now());
            patients.setUpdated_at(LocalDate.now());
            patients.setUser(user);
            patientRepository.save(patients);

            return "Patients Registered Successfully!";

        } else if (dto.getRole() == Users.Role.DOCTOR) {

            Doctors doctor = new Doctors();
            doctor.setFullName(dto.getFullName());
            doctor.setPhoneNumber(dto.getPhoneNumber());
            doctor.setEmail(dto.getEmail());
            doctor.setSpecialty(dto.getSpecialty());
            doctor.setUser(user);

            // Fetch department from DB using ID
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            doctor.setDepartment(department);

            doctorRepository.save(doctor);

            return "Doctor Registered Successfully!";
        } else {
            return "User Not Registered!";
        }

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