package com.education.system.service;

import com.education.system.dto.LoginRequest;
import com.education.system.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public LoginResponse login(LoginRequest loginRequest){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(loginRequest.getUsername());
        loginResponse.setToken("token");
        return loginResponse;
    }
}
