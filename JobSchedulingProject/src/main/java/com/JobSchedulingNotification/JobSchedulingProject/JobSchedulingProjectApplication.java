package com.JobSchedulingNotification.JobSchedulingProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JobSchedulingProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobSchedulingProjectApplication.class, args);


	}

}
