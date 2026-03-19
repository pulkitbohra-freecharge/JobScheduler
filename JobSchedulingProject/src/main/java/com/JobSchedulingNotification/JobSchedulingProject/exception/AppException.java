package com.JobSchedulingNotification.JobSchedulingProject.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException{

    private final String errorCode;
    private final int status;

    public AppException(String errorCode, String message, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }


}
