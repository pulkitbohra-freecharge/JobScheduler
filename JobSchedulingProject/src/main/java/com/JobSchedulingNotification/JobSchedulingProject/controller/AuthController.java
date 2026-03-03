package com.JobSchedulingNotification.JobSchedulingProject.controller;

import com.JobSchedulingNotification.JobSchedulingProject.dto.request.LoginRequest;
import com.JobSchedulingNotification.JobSchedulingProject.dto.request.RegisterRequest;
import com.JobSchedulingNotification.JobSchedulingProject.dto.response.LoginResponse;
import com.JobSchedulingNotification.JobSchedulingProject.dto.response.RegisterResponse;
import com.JobSchedulingNotification.JobSchedulingProject.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Component
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request){
        log.info("Register API called for email: {}", request.getEmail());

        try {
            RegisterResponse response = authService.register(request);
            log.info("User registered successfully: email={}, username={}, userId={}",
                    response.getEmail(), response.getUsername(), response.getUuid());
            return response;
        } catch (Exception ex) {
            log.error("Error while registering user: username={}", request.getUsername(), ex);
            throw ex;
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        log.info("Login API called for email: {}", request.getEmail());

        try {
            LoginResponse response = authService.login(request);
            log.info("User login successful:email={}, username={}, userId={}",
                    response.getEmail(),response.getUsername(), response.getUuid());
            return response;
        } catch (Exception ex) {
            log.error("Login failed for emaill={}", request.getEmail(), ex);
            throw ex;
        }
    }
}
