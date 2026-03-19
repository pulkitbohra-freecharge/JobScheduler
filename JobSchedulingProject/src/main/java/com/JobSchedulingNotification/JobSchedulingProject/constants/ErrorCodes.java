package com.JobSchedulingNotification.JobSchedulingProject.constants;

public class ErrorCodes {
    public static final String USER_NOT_FOUND_CODE = "ERR001";
    public static final String USER_NOT_FOUND_MSG = "User not found";

    public static final String INVALID_CREDENTIALS_CODE = "ERR002";
    public static final String INVALID_CREDENTIALS_MSG = "Invalid credentials";

    public static final String EMAIL_ALREADY_EXISTS_CODE = "ERR003";
    public static final String EMAIL_ALREADY_EXISTS_MSG = "Email already exists";

    public static final String ROLE_NOT_FOUND_CODE = "ERR004";
    public static final String ROLE_NOT_FOUND_MSG = "Role not found";

    public static final String ADMIN_ALREADY_EXISTS_CODE = "ERR005";
    public static final String ADMIN_ALREADY_EXISTS_MSG = "Admin already exists";

    public static final String ADMIN_ROLE_CHANGE_NOT_ALLOWED_CODE = "ERR006";
    public static final String ADMIN_ROLE_CHANGE_NOT_ALLOWED_MSG = "Admin role change not allowed";

    public static final String JOB_NOT_FOUND_CODE = "ERR007";
    public static final String JOB_NOT_FOUND_MSG = "Job not found";

    public static final String NO_JOBS_FOUND_CODE = "ERR008";
    public static final String NO_JOBS_FOUND_MSG = "No jobs found in the system";

    public static final String INVALID_CRON_CODE = "ERR009";
    public static final String INVALID_CRON_MSG = "Cron expression cannot be empty";

    public static final String JOB_HISTORY_NOT_FOUND_CODE = "ERR010";
    public static final String JOB_HISTORY_NOT_FOUND_MSG = "No execution history found for job";

    public static final String NEXT_RUN_NOT_FOUND_CODE = "ERR011";
    public static final String NEXT_RUN_NOT_FOUND_MSG = "Next run time not scheduled";

    public static final String INVALID_UUID_CODE = "ERR012";
    public static final String INVALID_UUID_MSG = "UUID not found";

    public static final String VALIDATION_ERROR_CODE = "ERR400";
    public static final String VALIDATION_ERROR_MSG = "Validation failed";
}
