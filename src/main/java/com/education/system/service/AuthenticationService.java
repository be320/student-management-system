package com.education.system.service;

import com.education.system.cache.entity.TokenCacheEntity;
import com.education.system.cache.entity.UserCacheEntity;
import com.education.system.cache.repo.TokenCacheRepository;
import com.education.system.cache.repo.UserCacheRepository;
import com.education.system.dto.LoginRequest;
import com.education.system.dto.LoginResponse;
import com.education.system.dto.SignupRequest;
import com.education.system.dto.SignupResponse;
import com.education.system.exception.InvalidPasswordException;
import com.education.system.exception.UserAlreadyExistingException;
import com.education.system.exception.UserNotFoundException;
import com.education.system.model.Student;
import com.education.system.repository.StudentRepository;
import com.education.system.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserCacheRepository userCacheRepository;

    @Autowired
    TokenCacheRepository tokenCacheRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StudentRepository studentRepository;

    public LoginResponse login(LoginRequest loginRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        Optional<UserCacheEntity> userCacheEntity = userCacheRepository.findById(loginRequest.getUsername());

        //Check in Cache
        if(userCacheEntity.isPresent()){
            String passwordInCache = userCacheEntity.get().getHashedPassword();
            if(!passwordEncoder.matches(loginRequest.getPassword(), passwordInCache)){
                throw new InvalidPasswordException();
            }
        }

        //Check in Database
        else {
            Student student = studentRepository.findByUsername(loginRequest.getUsername());
            if(student == null)
                throw new UserNotFoundException();
            else{
                if(!passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())){
                    throw new InvalidPasswordException();
                }
            }
        }
        String token = generateToken(loginRequest.getUsername(), "student");
        return new LoginResponse(loginRequest.getUsername(), token);
    }



    public SignupResponse signup(SignupRequest signupRequest) throws NoSuchAlgorithmException, InvalidKeyException {
        Optional<UserCacheEntity> userCacheEntity = userCacheRepository.findById(signupRequest.getUsername());

        //Check in Cache
        if(userCacheEntity.isPresent()){
            throw new UserAlreadyExistingException();
        }

        //Check in Database
        else if(studentRepository.findByUsername(signupRequest.getUsername()) != null){
            throw new UserAlreadyExistingException();
        }

        //Save in Database and  Cache
        else {
            String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());
            studentRepository.save(new Student(signupRequest.getName(), signupRequest.getUsername(), hashedPassword));
            userCacheRepository.save(new UserCacheEntity(signupRequest.getUsername(), hashedPassword));
        }
        String token = generateToken(signupRequest.getUsername(), "student");
        return new SignupResponse(signupRequest.getUsername(), token);
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
