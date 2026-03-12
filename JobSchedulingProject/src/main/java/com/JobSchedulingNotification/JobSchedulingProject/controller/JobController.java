package com.JobSchedulingNotification.JobSchedulingProject.controller;

import com.JobSchedulingNotification.JobSchedulingProject.dto.request.JobRequest;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Job;
import com.JobSchedulingNotification.JobSchedulingProject.entity.JobExecutionHistory;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public Job createJobs(@RequestBody JobRequest request, HttpServletRequest httpRequest){

        return jobService.createJob(request.getName(), request.getCronExpression(), request.getWebHookUrl());

    }


    @GetMapping
    public List<Job> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteJobById(@PathVariable Long id){
        jobService.deleteJob(id);

        return "Job with " + id + " deleted Successfully";
    }

    @PutMapping("/{id}/updateTiming")
    public Job updateTiming(@PathVariable Long id, @RequestParam String cronExpression ){
        return jobService.updateJobTiming(id,cronExpression);
    }

    @PutMapping("/{id}/pause")
    public Job pauseJob(@PathVariable Long id){
        return jobService.pauseJob(id);
    }

    @PutMapping("/{id}/resume")
    public Job resumeJob(@PathVariable Long id){
        return jobService.resumeJob(id);
    }


    @GetMapping("/{id}/history")
    public List<JobExecutionHistory> getHistoryById(@PathVariable Long id){

        return jobService.getJobHistory(id);
    }

    @GetMapping("/history")
    public List<JobExecutionHistory> getHistory(){
        return jobService.getAllJobHistory();
    }

    @GetMapping("/{id}/next-run")
    public LocalDateTime getNextRun(@PathVariable Long id){
        return jobService.getNextExecutionTime(id);
    }

}
