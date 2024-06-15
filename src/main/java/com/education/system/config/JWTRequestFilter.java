package com.education.system.config;

import com.education.system.exception.InvalidTokenException;
import com.education.system.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String locale = request.getHeader("X-Locale");
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            try {
                String jwtToken = requestTokenHeader.substring(7);
                Claims claims = tokenService.getAllClaimsFromToken(jwtToken);
                String issuer = claims.getIssuer();
                String username = claims.getSubject();
                if(issuer.equals("web") && tokenService.isTokenValid(username, jwtToken)){
                   String role = (String) claims.get("roles");
                   List<GrantedAuthority> authorities = new ArrayList<>();
                   authorities.add(new SimpleGrantedAuthority(role));
                   SecurityContextHolder.getContext().setAuthentication(new CustomAuthenticationToken(jwtToken, authorities));
                }
                else{
                    SecurityContextHolder.getContext().setAuthentication(new CustomAuthenticationToken(jwtToken, null));
                }
            }
            catch (ExpiredJwtException ex){
                logger.error("Token Expired : " + ex.getMessage());
                throw new InvalidTokenException();
            }
            catch (Exception ex){
                logger.error("Token Invalid : " + ex.getMessage());
                throw new InvalidTokenException();
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
