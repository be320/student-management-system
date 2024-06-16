package com.education.system.controller;

import com.education.system.dto.course.CreateCourseRequest;
import com.education.system.dto.course.CreateCourseResponse;
import com.education.system.dto.course.UploadScheduleResponse;
import com.education.system.dto.course.ViewCoursesResponse;
import com.education.system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<ViewCoursesResponse> viewCourses(){
        ViewCoursesResponse viewCoursesResponse = courseService.viewCourses();
        return new ResponseEntity<>(viewCoursesResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreateCourseResponse> createCourse(@RequestBody CreateCourseRequest createCourseRequest) {
        CreateCourseResponse createCourseResponse = courseService.createCourse(createCourseRequest);
        return new ResponseEntity<>(createCourseResponse, HttpStatus.OK);
    }

//    @PostMapping("/{courseCode}/upload-schedule")
//    public ResponseEntity<UploadScheduleResponse> uploadSchedule(@PathVariable String courseCode, @RequestParam("schedule") MultipartFile schedule) {
//        UploadScheduleResponse uploadScheduleResponse = courseService.uploadSchedule(courseCode, schedule);
//        return new ResponseEntity<>(uploadScheduleResponse, HttpStatus.OK);
//    }
}
