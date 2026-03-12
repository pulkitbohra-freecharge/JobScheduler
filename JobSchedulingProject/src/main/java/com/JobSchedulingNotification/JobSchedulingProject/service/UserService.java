package com.JobSchedulingNotification.JobSchedulingProject.service;

import com.JobSchedulingNotification.JobSchedulingProject.entity.Role;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.repository.RoleRepository;
import com.JobSchedulingNotification.JobSchedulingProject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public String upgradeRole(UUID uuid, String rolename){

        User user=userRepository.findByUuid(uuid).orElseThrow(()->new RuntimeException("Invalid UUID"));

        Role role=roleRepository.findByName(rolename.toUpperCase()).orElseThrow(()->new RuntimeException("Invalid Role"));

        user.setRole(role);
        userRepository.save(user);

        return "Role Upgraded to "+rolename;
    }
}
