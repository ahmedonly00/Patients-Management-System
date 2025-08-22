package com.patients.patientsMgt.services;

import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.model.PrescriptionDetail;
import com.patients.patientsMgt.repository.PrescriptionDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionDetailService {
    @Autowired
    private PrescriptionDetailRepository prescriptionDetailRepository;

    public Page<PrescriptionDetail> getAllPrescriptionDetails(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return prescriptionDetailRepository.findAll(pageable);
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