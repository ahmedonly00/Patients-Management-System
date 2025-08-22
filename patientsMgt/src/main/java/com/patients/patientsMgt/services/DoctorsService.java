package com.patients.patientsMgt.services;

import com.patients.patientsMgt.dto.DoctorsDTO;
import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.model.Doctors;
import com.patients.patientsMgt.model.Users;
import com.patients.patientsMgt.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorsService {
    @Autowired
    private DoctorsRepository doctorsRepository;

    public Page<Doctors> getAllDoctors(int page, int size,String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return doctorsRepository.findAll(pageable);
    }

    public Optional<Doctors> getDoctorById(Long id) {

        return doctorsRepository.findById(id);
    }

    public Doctors findByUser(Users user) {
        return doctorsRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found for user: " + user.getEmail()));
    }

    public Doctors saveDoctor(Doctors doctor) {

        return doctorsRepository.save(doctor);
    }


    public void deleteDoctor(Long id) {

        doctorsRepository.deleteById(id);
    }


    public DoctorsDTO getDoctorInfo(String email) {
        Doctors doctors = doctorsRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Email", email));

        return new DoctorsDTO(
            doctors.getDoctorId(),
            doctors.getFullName(),
            doctors.getSpecialty(),
            doctors.getDepartment(),
            doctors.getEmail(),
            doctors.getPhoneNumber());


    }
} 