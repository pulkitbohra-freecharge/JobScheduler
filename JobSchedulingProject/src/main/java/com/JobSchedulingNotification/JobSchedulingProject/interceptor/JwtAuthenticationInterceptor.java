package com.JobSchedulingNotification.JobSchedulingProject.interceptor;

import com.JobSchedulingNotification.JobSchedulingProject.entity.User;
import com.JobSchedulingNotification.JobSchedulingProject.repository.UserRepository;
import com.JobSchedulingNotification.JobSchedulingProject.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

        private final JwtTokenProvider jwtTokenProvider;
        private final UserRepository userRepository;

        @Override
        public boolean preHandle(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Object handler ) throws Exception{


            String path= request.getRequestURI();
            System.out.println("before authentication");
            if(path.contains("/auth"))return true;
           // System.out.println("after authentication");
            String header = request.getHeader("Authorization");

            if(header ==null || !header.startsWith("Bearer")){
               // System.out.println("Header hull");
                response.setStatus(401);

                return false;
            }
            System.out.println("interceptor Running");

            String token= header.substring(7);

            Claims claims =jwtTokenProvider.validateToken(token);

            User user =userRepository.findByUuid(UUID.fromString(claims.getSubject()))
                    .orElseThrow(()-> new RuntimeException("User not found"));
            request.setAttribute("loggedInUser",user);

            return true;
        }

}
