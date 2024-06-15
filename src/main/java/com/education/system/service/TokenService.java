package com.education.system.service;

import com.education.system.cache.entity.TokenCacheEntity;
import com.education.system.cache.repo.TokenCacheRepository;
import com.education.system.util.SecurityUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class TokenService {

    @Autowired
    TokenCacheRepository tokenCacheRepository;

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

    public boolean isTokenValid(String username, String token) throws NoSuchAlgorithmException, InvalidKeyException {
        String hmacTokenFromUser = SecurityUtil.hmacSHA256(SecurityUtil.secretHmac, token);
        Optional<TokenCacheEntity> hmacTokenFromRedis = tokenCacheRepository.findById(username);
        return hmacTokenFromRedis.filter(tokenCacheEntity -> hmacTokenFromUser.equals(tokenCacheEntity.getTokenHash())).isPresent();
    }

}
