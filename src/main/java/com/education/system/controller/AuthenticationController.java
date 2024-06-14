package com.education.system.controller;

import com.education.system.dto.LoginRequest;
import com.education.system.dto.LoginResponse;
import com.education.system.service.AuthenticationService;
import com.education.system.service.TokenService;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login( @RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    private ResponseCookie setCookie(String cookieHash){
        return ResponseCookie.from("tokenval", cookieHash)
                .secure(true)
                .httpOnly(true)
                .maxAge(300)
                .path("/")
                .build();
    }


}
