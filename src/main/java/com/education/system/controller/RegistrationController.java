package com.education.system.controller;

import com.education.system.dto.registration.CourseRegistrationRequest;
import com.education.system.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerCourse(@RequestBody CourseRegistrationRequest courseRegistrationRequest) {
        String registerCourseResponse = registrationService.registerCourse(courseRegistrationRequest.getUsername(), courseRegistrationRequest.getCourseCode());
        return new ResponseEntity<>(registerCourseResponse, HttpStatus.OK);
    }

//    @DeleteMapping("/cancel")
//    public ResponseEntity<String> cancelCourseRegistration(@RequestBody CourseRegistrationRequest courseRegistrationRequest) {
//        String cancelRegistrationResponse = registrationService.cancelCourseRegistration(courseRegistrationRequest.getUsername(), courseRegistrationRequest.getCourseCode());
//        return new ResponseEntity<>(cancelRegistrationResponse, HttpStatus.OK);
//    }
}
