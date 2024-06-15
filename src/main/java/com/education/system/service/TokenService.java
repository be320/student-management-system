package com.education.system.service;

import com.education.system.util.SecurityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class TokenService {

    @Value("${jwt.validity}")
    private long tokenValidityTimeInSeconds = 0;

    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityTimeInSeconds * 10000))
                .signWith(SignatureAlgorithm.HS512, SecurityUtil.secretKey).compact();
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(SecurityUtil.secretKey).parseClaimsJws(token).getBody();
    }

}
