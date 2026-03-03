package com.JobSchedulingNotification.JobSchedulingProject.repository;

import com.JobSchedulingNotification.JobSchedulingProject.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> findByActiveTrueAndNextRunTimeLessThanEqual(LocalDateTime now);

}
