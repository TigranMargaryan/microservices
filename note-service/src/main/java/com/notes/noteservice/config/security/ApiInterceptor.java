package com.notes.noteservice.config.security;

import com.notes.noteservice.context.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class ApiInterceptor implements HandlerInterceptor, WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(ApiInterceptor.class);

    public static final String HTTP_USER_SERVICE_HOST = "http://localhost:8080/actuator/health";

    private final UserDetailService detailService;

    @Autowired
    public ApiInterceptor(UserDetailService detailService) {
        this.detailService = detailService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        checkUserServiceStatus();
        
        if (detailService.getToken() != null) {
            return true;
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            LOG.error("Api access failed");
            return false;
        }
    }

    private void checkUserServiceStatus() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.getForObject(HTTP_USER_SERVICE_HOST, String.class);
        } catch (ResourceAccessException e) {
            this.detailService.setNull();
        }
    }
}
