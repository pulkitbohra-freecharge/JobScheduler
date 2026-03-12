package com.JobSchedulingNotification.JobSchedulingProject.controller;


import com.JobSchedulingNotification.JobSchedulingProject.service.UserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PutMapping("/upgrade/{uuid}")
    public String upgradeRole(@PathVariable String uuid, @RequestParam String role) {

        UUID userId;
        if(role.equalsIgnoreCase("admin")){
            return "Cannot Upgrade to Admin";
        }
        try {
            userId = UUID.fromString(uuid);
        } catch (Exception e) {
            throw new RuntimeException("Invalid UUID format");
        }

        log.info("Role upgrade API called for userId={}, requestedRole={}", userId, role);

        return userService.upgradeRole(userId, role);
    }

}
