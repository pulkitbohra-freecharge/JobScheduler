package com.JobSchedulingNotification.JobSchedulingProject.service;


import com.JobSchedulingNotification.JobSchedulingProject.constants.JobStatus;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Job;
import com.JobSchedulingNotification.JobSchedulingProject.entity.JobExecutionHistory;
import com.JobSchedulingNotification.JobSchedulingProject.repository.JobExecutionHistoryRepository;
import com.JobSchedulingNotification.JobSchedulingProject.repository.JobRepository;
import com.JobSchedulingNotification.JobSchedulingProject.util.CronUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobScanner {

    private final JobRepository jobRepository;
    private final JobExecutionHistoryRepository jobExecutionHistoryRepository;
    private final WebHookService webHookService;
    private final CronUtils cronUtils;

    @Scheduled(fixedRate = 60000)
    public void scanAndExecute(){
        List<Job> jobs= jobRepository.findByActiveTrueAndNextRunTimeLessThanEqual(LocalDateTime.now());

        for(Job job: jobs){
            JobExecutionHistory history= new JobExecutionHistory();
            history.setJobId(job.getId());
            history.setExectuedAt(LocalDateTime.now());

            try{
                webHookService.send(job.getWebHookUrl(),"Job Triggered "+job.getName());
                history.setStatus(JobStatus.Success);
                history.setResponse("Notification Send");

                job.setStatus(JobStatus.Success);
                job.setRetryCount(0);

                job.setNextRunTime(cronUtils.getExecutionTime(job.getCronExpression()));

                log.info("Job {} executed", job.getId());
            }
            catch (Exception e){


                history.setResponse(e.getMessage());
                log.error("Job {} failed", job.getId(), e);

                if(job.getRetryCount()<job.getMaxRetries()){
                    history.setStatus(JobStatus.Retry_Scheduled);
                    job.setStatus(JobStatus.Retry_Scheduled);
                    job.setRetryCount(job.getRetryCount()+1);
                    job.setNextRunTime(LocalDateTime.now().plusMinutes(2L *job.getRetryCount()));
                }

                else{
                    history.setStatus(JobStatus.Failed);
                    job.setStatus(JobStatus.Failed);
                    job.setActive(false);
                }
            }

            jobExecutionHistoryRepository.save(history);



            jobRepository.save(job);
        }
    }


}
