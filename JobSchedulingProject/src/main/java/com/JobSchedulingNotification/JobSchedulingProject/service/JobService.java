package com.JobSchedulingNotification.JobSchedulingProject.service;

import com.JobSchedulingNotification.JobSchedulingProject.constants.ErrorCodes;
import com.JobSchedulingNotification.JobSchedulingProject.constants.JobStatus;
import com.JobSchedulingNotification.JobSchedulingProject.dto.request.JobRequest;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Job;
import com.JobSchedulingNotification.JobSchedulingProject.entity.JobExecutionHistory;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.exception.AppException;
import com.JobSchedulingNotification.JobSchedulingProject.exception.ResourceNotFoundException;
import com.JobSchedulingNotification.JobSchedulingProject.repository.JobExecutionHistoryRepository;
import com.JobSchedulingNotification.JobSchedulingProject.repository.JobRepository;
import com.JobSchedulingNotification.JobSchedulingProject.util.CronUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final CronUtils cronUtils;
    private final JobExecutionHistoryRepository jobExecutionHistoryRepository;
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

    public List<Job> getAllJobs(){
        List<Job> jobs= jobRepository.findAll();

        if(jobs.isEmpty()){
            throw new AppException(ErrorCodes.NO_JOBS_FOUND_CODE,ErrorCodes.NO_JOBS_FOUND_MSG,404);
        }

        return jobs;
    }

    public Job getJobById(Long jobId){

        return jobRepository.findById(jobId).
                orElseThrow(()->new AppException(ErrorCodes.JOB_NOT_FOUND_CODE,ErrorCodes.JOB_NOT_FOUND_MSG + "with id"+ jobId,404));
    }


    public void deleteJob(Long jobId){
        Job job=getJobById(jobId);
        jobRepository.delete(job);
    }


    public Job updateJobTiming(Long id, String cronExpression){

        Job job =getJobById(id);

        if(cronExpression == null || cronExpression.isEmpty()){
            throw new AppException(ErrorCodes.INVALID_CRON_CODE,ErrorCodes.INVALID_CRON_MSG,400);
        }

        job.setCronExpression(cronExpression);

        return jobRepository.save(job);
    }

    public Job pauseJob(Long id){
        Job job= getJobById(id);


        job.setActive(false);

        return jobRepository.save(job);
    }

    public Job resumeJob(Long id){


        Job job= getJobById(id);
        if(job.getRetryCount()< job.getMaxRetries())
            job.setActive(true);


        return jobRepository.save(job);
    }


    public List<JobExecutionHistory> getJobHistory(Long id){
        getJobById(id);

        List<JobExecutionHistory> jobHistory= jobExecutionHistoryRepository.findByJobId(id);

        if(jobHistory.isEmpty()){
            throw new AppException(ErrorCodes.JOB_HISTORY_NOT_FOUND_CODE,ErrorCodes.JOB_HISTORY_NOT_FOUND_MSG+ "with id"+id,404);
        }

        return jobHistory;
    }

    public List<JobExecutionHistory> getAllJobHistory(){
        return jobExecutionHistoryRepository.findAll();
    }

    public LocalDateTime getNextExecutionTime(Long id){
        Job job =getJobById(id);

        if(job.getNextRunTime() == null){
            throw new AppException(ErrorCodes.NEXT_RUN_NOT_FOUND_CODE,ErrorCodes.NEXT_RUN_NOT_FOUND_MSG+"with id"+id,404);
        }

        return job.getNextRunTime();
    }

}
