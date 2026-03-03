package com.JobSchedulingNotification.JobSchedulingProject.interceptor;

import com.JobSchedulingNotification.JobSchedulingProject.constants.RoleConstants;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class RoleAuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (request.getRequestURI().contains("/auth")) {
            return true;
        }
        User user= (User) request.getAttribute("loggedInUser");

        if(user==null){
            response.setStatus(401);
            return false;
        }
        if(request.getRequestURI().contains("/admin")){

            if (!user.getRole().getName().equals(RoleConstants.Admin)) {
                response.setStatus(403);
                return false;
            }
        }

        if (request.getRequestURI().contains("/jobs")) {
            String role = user.getRole().getName();

            if (!(role.equalsIgnoreCase(RoleConstants.Admin) ||
                    role.equalsIgnoreCase(RoleConstants.Manager))) {
                response.setStatus(403);
                return false;
            }
        }
        return true;
    }
}
