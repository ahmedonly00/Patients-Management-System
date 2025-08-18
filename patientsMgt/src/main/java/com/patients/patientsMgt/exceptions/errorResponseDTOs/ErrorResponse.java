package com.patients.patientsMgt.exceptions.errorResponseDTOs;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String timeStamp;
    private int status;
    private String error;
    private String errorCode;
    private String message;
    private String path;
    private Map<String, String> validationErrors;


    public ErrorResponse(String timeStamp, int status, String error, String errorCode, String message, String path) {
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.status = status;
        this.error = HttpStatus.valueOf(status).getReasonPhrase();
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
    }

    public ErrorResponse(String message, String errorCode, int httpStatus, String requestURI) {

    }
}
