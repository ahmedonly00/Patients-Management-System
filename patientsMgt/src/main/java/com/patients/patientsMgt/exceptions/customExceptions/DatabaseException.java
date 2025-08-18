package com.patients.patientsMgt.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DatabaseException extends BaseException {
    public DatabaseException(String message) {
        super(message, "DATABASE_ERROR", 500);
    }
}
