package com.JobSchedulingNotification.JobSchedulingProject.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LockProviderConfig {

    @Bean
    public LockProvider lockProvider(DataSource dataSource){

        return new JdbcTemplateLockProvider(
                JdbcTemplateLockProvider.Configuration.builder().withJdbcTemplate(new org.springframework.jdbc.core.JdbcTemplate(dataSource)).usingDbTime().build()
        );
    }

}
