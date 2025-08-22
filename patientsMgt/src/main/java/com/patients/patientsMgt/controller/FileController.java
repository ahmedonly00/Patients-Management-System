package com.patients.patientsMgt.controller;

import com.patients.patientsMgt.exceptions.customExceptions.ResourceNotFoundException;
import com.patients.patientsMgt.model.FileEntity;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.repository.PatientsRepository;
import com.patients.patientsMgt.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PatientsRepository patientsRepository;

    @PostMapping("/upload/{patientId}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @PathVariable Long patientId) {
        try {

            Patients patients = patientsRepository.findByPatientId(patientId)
                            .orElseThrow(() -> new ResourceNotFoundException("Patient Not Found"));

            fileService.storeFile(file, patients);
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not upload file: " + file.getOriginalFilename());
        }
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        FileEntity fileEntity = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .body(fileEntity.getData());
    }

    @GetMapping("/all")
    public ResponseEntity<List<FileEntity>> listFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }
}
