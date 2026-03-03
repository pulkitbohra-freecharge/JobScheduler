package com.JobSchedulingNotification.JobSchedulingProject.config;


import com.JobSchedulingNotification.JobSchedulingProject.constants.RoleConstants;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Role;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.repository.RoleRepository;
import com.JobSchedulingNotification.JobSchedulingProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args){

           Role adminRole = roleRepository.findByName(RoleConstants.Admin)
                .orElseGet(() -> roleRepository.save(new Role(null,RoleConstants.Admin)));

        Role managerRole = roleRepository.findByName(RoleConstants.Manager)
                .orElseGet(() -> roleRepository.save(new Role(null, RoleConstants.Manager)));


        Role userRole = roleRepository.findByName(RoleConstants.Basic_User)
                .orElseGet(() -> roleRepository.save(new Role(null, RoleConstants.Basic_User)));


        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole(adminRole);
            admin.setUuid(UUID.randomUUID());
            admin.setActive(true);

            userRepository.save(admin);

            System.out.println("Default ADMIN created → username: admin | password: admin123");
        }
    }
}
