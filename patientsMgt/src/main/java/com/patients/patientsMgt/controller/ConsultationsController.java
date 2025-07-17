package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.model.Consultations;
import com.patients.patientsMgt.services.ConsultationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultations")
public class ConsultationsController {
    @Autowired
    private ConsultationsService consultationsService;

    @GetMapping(value = "/all")
    public List<Consultations> getAllConsultations() {
        return consultationsService.getAllConsultations();
    }

    @GetMapping(value = "getConsultationById/{id}")
    public Optional<Consultations> getConsultationById(@PathVariable Long id) {
        return consultationsService.getConsultationById(id);
    }

    @PostMapping(value = "/createConsultation")
    public Consultations createConsultation(@RequestBody Consultations consultation) {
        return consultationsService.saveConsultation(consultation);
    }

    @PutMapping(value = "/updateConsultation/{id}")
    public Consultations updateConsultation(@PathVariable Long id, @RequestBody Consultations consultation) {
        consultation.setConsultationId(id);
        return consultationsService.saveConsultation(consultation);
    }

    @DeleteMapping(value = "/deleteConsultation/{id}")
    public void deleteConsultation(@PathVariable Long id) {
        consultationsService.deleteConsultation(id);
    }
} 