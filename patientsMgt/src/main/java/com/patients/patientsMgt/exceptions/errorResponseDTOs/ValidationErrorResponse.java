package com.patients.patientsMgt.exceptions.errorResponseDTOs;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponse {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private Map<String, String> fieldErrors;

    public ValidationErrorResponse(String message, String path, Map<String, String> fieldErrors) {
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.status = 400;
        this.error = "Bad Request";
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

}
