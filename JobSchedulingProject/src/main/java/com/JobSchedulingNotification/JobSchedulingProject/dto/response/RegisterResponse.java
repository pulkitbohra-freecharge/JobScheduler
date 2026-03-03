package com.JobSchedulingNotification.JobSchedulingProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String message;
    private String username;
    private UUID uuid;
    private String email;
}
