package com.education.system.service;

import com.education.system.cache.entity.TokenCacheEntity;
import com.education.system.cache.repo.TokenCacheRepository;
import com.education.system.cache.repo.UserCacheRepository;
import com.education.system.dto.LoginRequest;
import com.education.system.dto.LoginResponse;
import com.education.system.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserCacheRepository userCacheRepository;

    @Autowired
    TokenCacheRepository tokenCacheRepository;

    public LoginResponse login(LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        String token = generateToken(loginRequest.getUsername(), "student");
        return new LoginResponse(loginRequest.getUsername(), token);
    }

    private String generateToken(String username, String role) throws NoSuchAlgorithmException, InvalidKeyException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", "web");
        claims.put("roles", role);
        String token =  tokenService.generateToken(claims, username);
        String hmacTokenString = SecurityUtil.hmacSHA256(SecurityUtil.secretHmac, token);
        tokenCacheRepository.save(new TokenCacheEntity(username, hmacTokenString));
        return token;
    }
}
