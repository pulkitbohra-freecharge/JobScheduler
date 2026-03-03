package com.JobSchedulingNotification.JobSchedulingProject.repository;

import com.JobSchedulingNotification.JobSchedulingProject.entity.JobExecutionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobExecutionHistoryRepository extends JpaRepository <JobExecutionHistory
        , Long> {


}
