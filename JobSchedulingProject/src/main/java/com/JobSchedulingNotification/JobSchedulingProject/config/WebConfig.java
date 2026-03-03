package com.JobSchedulingNotification.JobSchedulingProject.config;

import com.JobSchedulingNotification.JobSchedulingProject.interceptor.JwtAuthenticationInterceptor;
import com.JobSchedulingNotification.JobSchedulingProject.interceptor.RoleAuthorizationInterceptor;
import com.JobSchedulingNotification.JobSchedulingProject.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtAuthenticationInterceptor jwtAuthenticationInterceptor;
    private final RoleAuthorizationInterceptor roleAuthorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(jwtAuthenticationInterceptor);
        registry.addInterceptor(roleAuthorizationInterceptor);
    }


}
