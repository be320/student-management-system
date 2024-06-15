package com.education.system.config;

import com.education.system.dto.ErrorResponse;
import com.education.system.util.enums.ErrorEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class AuthEntryPoint implements AuthenticationEntryPoint {
    private static final Logger log = LoggerFactory.getLogger(AuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("Secuirty Error Happened : " + authException.getMessage() + " and the cause is : " + authException.getCause());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(String.valueOf(new ErrorResponse(ErrorEnum.UNAUTHORIZED.code, ErrorEnum.UNAUTHORIZED.message)));
    }
}
