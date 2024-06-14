package com.education.system.controller;

import com.education.system.dto.LoginRequest;
import com.education.system.dto.LoginResponse;
import com.education.system.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<*> login( @RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseCookie cookie = setCookie()
        return new ResponseEntity<LoginResponse>(loginResponse, )
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
