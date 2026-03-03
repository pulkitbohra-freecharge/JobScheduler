package com.JobSchedulingNotification.JobSchedulingProject.util;

import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CronUtils {

    public LocalDateTime getExecutionTime(String cronExression){
        CronExpression expression = CronExpression.parse(cronExression);
        return expression.next(LocalDateTime.now());
    }
}
