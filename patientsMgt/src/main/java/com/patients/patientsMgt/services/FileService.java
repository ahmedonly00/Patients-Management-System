package com.patients.patientsMgt.services;

import com.patients.patientsMgt.model.FileEntity;
import com.patients.patientsMgt.model.Patients;
import com.patients.patientsMgt.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileEntity storeFile(MultipartFile file, Patients patient) throws IOException {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(file.getOriginalFilename());
        fileEntity.setFileType(file.getContentType());
        fileEntity.setData(file.getBytes());  // or save to filesystem/cloud
        fileEntity.setPatient(patient);

        return fileRepository.save(fileEntity);
    }

    public FileEntity getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }
}
