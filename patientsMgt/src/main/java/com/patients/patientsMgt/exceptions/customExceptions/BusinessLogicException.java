package com.patients.patientsMgt.exceptions.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessLogicException extends BaseException{
    public BusinessLogicException(String message) {
        super(message, "BUSINESS_LOGIC_ERROR", 409);
    }
}
