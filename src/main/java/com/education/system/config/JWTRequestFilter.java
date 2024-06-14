package com.education.system.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String locale = request.getHeader("X-Locale");
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            try {
                String jwtToken = requestTokenHeader.substring(7);
                Claims claims =
            }
            catch (ExpiredJwtException ex){
                logger.error("Token Expired : " + ex.getMessage());
            }
            catch (Exception ex){
                logger.error("Token Invalid : " + ex.getMessage());
            }
        }
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            filterChain.doFilter(request, response);
        }
    }
}
