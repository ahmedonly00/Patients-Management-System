package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.services.PatientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientsController {
    @Autowired
    private PatientsService patientsService;

    @GetMapping(value = "/all")
    public List<Patients> getAllPatients() {
        return patientsService.getAllPatients();
    }

    @GetMapping(value = "getPatientById/{id}")
    public Optional<Patients> getPatientById(@PathVariable Long id) {
        return patientsService.getPatientById(id);
    }

    @PostMapping(value = "/createPatient")
    public Patients createPatient(@RequestBody Patients patient) {
        return patientsService.savePatient(patient);
    }

    @PutMapping(value = "/updatePatient/{id}")
    public Patients updatePatient(@PathVariable Long id, @RequestBody Patients patient) {
        patient.setPatientId(id);
        return patientsService.savePatient(patient);
    }

    @DeleteMapping(value = "/deletePatient/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientsService.deletePatient(id);
    }
} 