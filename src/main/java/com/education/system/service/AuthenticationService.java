package com.education.system.service;

import com.education.system.cache.entity.TokenCacheEntity;
import com.education.system.cache.entity.UserCacheEntity;
import com.education.system.cache.repo.TokenCacheRepository;
import com.education.system.cache.repo.UserCacheRepository;
import com.education.system.dto.auth.LoginRequest;
import com.education.system.dto.auth.LoginResponse;
import com.education.system.dto.auth.SignupRequest;
import com.education.system.dto.auth.SignupResponse;
import com.education.system.exception.InvalidPasswordException;
import com.education.system.exception.EntityAlreadyExistingException;
import com.education.system.exception.EntityNotFoundException;
import com.education.system.model.Student;
import com.education.system.repository.StudentRepository;
import com.education.system.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);

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

        //Check in Cache
        Optional<UserCacheEntity> userCacheEntity = userCacheRepository.findById(loginRequest.getUsername());
        if(userCacheEntity.isPresent()){
            log.info("User: {} found in the cache", loginRequest.getUsername());
            String passwordInCache = userCacheEntity.get().getHashedPassword();
            validatePassword(loginRequest.getPassword(), passwordInCache, loginRequest.getUsername());
        }

        //Check in Database
        else {
            Student student = studentRepository.findByUsername(loginRequest.getUsername());
            if(student == null)
                throw new EntityNotFoundException();
            else{
                validatePassword(loginRequest.getPassword(), student.getPassword(), loginRequest.getUsername());
            }
        }
        String token = generateToken(loginRequest.getUsername(), "student");
        return new LoginResponse(loginRequest.getUsername(), token);
    }



    public SignupResponse signup(SignupRequest signupRequest) throws NoSuchAlgorithmException, InvalidKeyException {

        //Check in Cache
        Optional<UserCacheEntity> userCacheEntity = userCacheRepository.findById(signupRequest.getUsername());
        if(userCacheEntity.isPresent()){
            throw new EntityAlreadyExistingException();
        }

        //Check in Database
        else if(studentRepository.findByUsername(signupRequest.getUsername()) != null){
            throw new EntityAlreadyExistingException();
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

    private void validatePassword(String rawPassword, String encodedPassword, String username) {
        if(!passwordEncoder.matches(rawPassword, encodedPassword)){
            log.error("User: {} password is wrong", username);
            throw new InvalidPasswordException();
        }
    }
}
