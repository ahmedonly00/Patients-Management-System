package com.patients.patientsMgt.services;

import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.model.PrescriptionDetail;
import com.patients.patientsMgt.repository.PrescriptionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionDetailService {
    @Autowired
    private PrescriptionDetailRepository prescriptionDetailRepository;

    public List<PrescriptionDetail> getAllPrescriptionDetails() {

        return prescriptionDetailRepository.findAll();
    }

    public PrescriptionDetail getPrescriptionDetailById(Long id) {
        return prescriptionDetailRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("PrescriptionDetail", "Id", id));
    }

    public PrescriptionDetail savePrescriptionDetail(PrescriptionDetail prescriptionDetail) {
        return prescriptionDetailRepository.save(prescriptionDetail);
    }

    public void deletePrescriptionDetail(Long id) {
        prescriptionDetailRepository.deleteById(id);
    }
} 