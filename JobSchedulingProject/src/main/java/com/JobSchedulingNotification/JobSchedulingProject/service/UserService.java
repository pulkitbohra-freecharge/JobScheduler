package com.JobSchedulingNotification.JobSchedulingProject.service;

import com.JobSchedulingNotification.JobSchedulingProject.constants.ErrorCodes;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Role;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.exception.AppException;
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

        User user=userRepository.findByUuid(uuid).orElseThrow(()->new AppException(ErrorCodes.INVALID_UUID_CODE,ErrorCodes.INVALID_UUID_MSG,404));

        Role role=roleRepository.findByName(rolename.toUpperCase()).orElseThrow(()->new AppException(ErrorCodes.ROLE_NOT_FOUND_CODE,ErrorCodes.ROLE_NOT_FOUND_MSG,401));

        user.setRole(role);
        userRepository.save(user);

        return "Role Upgraded to "+rolename;
    }
}
