package com.JobSchedulingNotification.JobSchedulingProject.dto.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JobRequest {
    private String name;
    private String cronExpression;
    private String webHookUrl;
}
