package com.JobSchedulingNotification.JobSchedulingProject.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationErrorResponse {

    private LocalDateTime timestamp;
    private String errorCode;
    private String message;
    private List<FieldError> errors;
    private int status;
}

