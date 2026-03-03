package com.JobSchedulingNotification.JobSchedulingProject.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Jobs")
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="cron_expression")
    private String cronExpression;

    @Column(name = "web_hook_url")
    private String webHookUrl;

    @Column(name="next_run_time")
    private LocalDateTime nextRunTime;

    private boolean active=true ;

    @Column(name = "retry_count", nullable = false)
    private int retryCount = 0;

    @Column(name = "max_retries", nullable = false)
    private int maxRetries = 3;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";
}
