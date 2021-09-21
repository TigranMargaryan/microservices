package com.notes.noteservice.config.security;

import com.notes.noteservice.context.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class ApiInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(ApiInterceptor.class);

    private final UserDetailService detailService;

    @Autowired
    public ApiInterceptor(UserDetailService detailService) {
        this.detailService = detailService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if (detailService.getToken() != null) {
            return true;
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
            LOG.error("Api access failed");
            return false;
        }
    }
}
