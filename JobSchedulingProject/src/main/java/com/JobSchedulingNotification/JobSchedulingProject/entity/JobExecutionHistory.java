package com.JobSchedulingNotification.JobSchedulingProject.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "JobExecutionHistory")
@Entity
@Data
public class JobExecutionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private LocalDateTime exectuedAt;

    private String status;

    @Column(length = 2000)
    private String response;
}

