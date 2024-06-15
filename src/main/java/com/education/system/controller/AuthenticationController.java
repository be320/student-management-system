package com.education.system.controller;

import com.education.system.dto.LoginRequest;
import com.education.system.dto.LoginResponse;
import com.education.system.dto.SignupRequest;
import com.education.system.dto.SignupResponse;
import com.education.system.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        SignupResponse signupResponse = authenticationService.signup(signupRequest);
        return new ResponseEntity<>(signupResponse, HttpStatus.OK);
    }

}
