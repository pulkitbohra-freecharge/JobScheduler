package com.JobSchedulingNotification.JobSchedulingProject.service;

import com.JobSchedulingNotification.JobSchedulingProject.constants.JobStatus;
import com.JobSchedulingNotification.JobSchedulingProject.dto.request.JobRequest;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Job;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.repository.JobRepository;
import com.JobSchedulingNotification.JobSchedulingProject.util.CronUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final CronUtils cronUtils;
    public Job createJob(String name, String cronExpression, String webhookUrl) {


        Job job = new Job();

        job.setName(name);
        job.setCronExpression(cronExpression);
        job.setWebHookUrl(webhookUrl);
        job.setNextRunTime(cronUtils.getExecutionTime(cronExpression));
        job.setRetryCount(0);
        job.setMaxRetries(3);
        job.setStatus(JobStatus.Pending);

        return jobRepository.save(job);

       // return "Job" +name+" created Successfully ";
    }
}
