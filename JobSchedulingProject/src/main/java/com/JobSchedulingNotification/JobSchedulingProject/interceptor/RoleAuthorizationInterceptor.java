package com.JobSchedulingNotification.JobSchedulingProject.interceptor;

import com.JobSchedulingNotification.JobSchedulingProject.constants.RoleConstants;
import com.JobSchedulingNotification.JobSchedulingProject.entity.Role;
import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;
import java.util.Map;

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

        String requestUri= request.getRequestURI();
        String method=request.getMethod();

        Role role=user.getRole();

        Map<String, List<String>> apiMap= role.getApiPermissionMap();

        if(apiMap==null){
            role.buildPermissionMap();
            apiMap=role.getApiPermissionMap();
        }

        List<String> allowedPaths= apiMap.get(method);

        if(allowedPaths==null){
            response.setStatus(403);
            return false;
        }

        boolean allowed= allowedPaths.stream()
                .anyMatch(path->requestUri.equals(path)
                        || requestUri.startsWith(path+"/"));

        if(!allowed){
            response.setStatus(403);
            return false;
        }

        return true;
    }
}
