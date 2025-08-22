package com.patients.patientsMgt.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntityDTO {
    private Long id;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;
}
