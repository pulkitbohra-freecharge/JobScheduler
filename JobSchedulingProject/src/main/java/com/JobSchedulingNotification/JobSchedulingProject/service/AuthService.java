package com.JobSchedulingNotification.JobSchedulingProject.service;

import com.JobSchedulingNotification.JobSchedulingProject.constants.RoleConstants;
import com.JobSchedulingNotification.JobSchedulingProject.dto.request.LoginRequest;
import com.JobSchedulingNotification.JobSchedulingProject.dto.request.RegisterRequest;
import com.JobSchedulingNotification.JobSchedulingProject.dto.response.LoginResponse;
import com.JobSchedulingNotification.JobSchedulingProject.dto.response.RegisterResponse;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Role;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.repository.RoleRepository;
import com.JobSchedulingNotification.JobSchedulingProject.repository.UserRepository;
import com.JobSchedulingNotification.JobSchedulingProject.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String man=RoleConstants.Manager;
    public RegisterResponse register(RegisterRequest request){
        Role role=roleRepository.findByName(RoleConstants.Basic_User).orElseThrow(()-> new RuntimeException("Role missing"));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());

        user.setPassword(encoder.encode(request.getPassword()));

        user.setRole(role);

        userRepository.save(user);

        return new RegisterResponse("User registered successfully", user.getUsername(), user.getUuid(),user.getEmail());

    }

    public LoginResponse login(LoginRequest request){
        User user= userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new RuntimeException("Invalid Email"));

        if(!encoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Invalid Password");
        }

        String token=jwtTokenProvider.generateToken(String.valueOf(user.getUuid()), user.getRole().getName());
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setUsername(user.getUsername());
        response.setUuid(user.getUuid());
        response.setEmail(user.getEmail());
        return response;
    }


}
