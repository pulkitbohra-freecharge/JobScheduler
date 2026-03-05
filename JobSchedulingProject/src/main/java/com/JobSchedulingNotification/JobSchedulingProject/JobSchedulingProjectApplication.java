package com.JobSchedulingNotification.JobSchedulingProject;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor ="10m")
public class JobSchedulingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSchedulingProjectApplication.class, args);


	}

}
