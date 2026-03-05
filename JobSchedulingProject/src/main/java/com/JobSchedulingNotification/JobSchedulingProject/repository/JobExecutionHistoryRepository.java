package com.JobSchedulingNotification.JobSchedulingProject.repository;

import com.JobSchedulingNotification.JobSchedulingProject.entity.JobExecutionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobExecutionHistoryRepository extends JpaRepository <JobExecutionHistory
        , Long> {

    List<JobExecutionHistory> findByJobId(Long jobId);
}
