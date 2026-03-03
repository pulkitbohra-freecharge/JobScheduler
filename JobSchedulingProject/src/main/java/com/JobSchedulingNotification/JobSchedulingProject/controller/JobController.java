package com.JobSchedulingNotification.JobSchedulingProject.controller;

import com.JobSchedulingNotification.JobSchedulingProject.dto.request.JobRequest;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Job;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public Job createJobs(@RequestBody JobRequest request,
                             HttpServletRequest httpRequest){

        return jobService.createJob(
                request.getName(),
                request.getCronExpression(),
                request.getWebHookUrl()
        );

    }
}
