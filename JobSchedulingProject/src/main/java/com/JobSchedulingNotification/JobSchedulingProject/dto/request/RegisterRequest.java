package com.JobSchedulingNotification.JobSchedulingProject.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterRequest {

    @NotBlank(message = "Email required")
    @Email(message = "Invalid format")
    private String email;
    @NotBlank(message = "Name required")
    private String username;
    @NotBlank(message = "Password required")
    private String password;
}
