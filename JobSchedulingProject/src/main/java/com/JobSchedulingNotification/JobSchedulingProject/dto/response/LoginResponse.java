package com.JobSchedulingNotification.JobSchedulingProject.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private UUID uuid;
    private String username;
    private String email;
}
