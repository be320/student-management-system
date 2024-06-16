package com.education.system.service;

import com.education.system.cache.entity.ScheduleCacheEntity;
import com.education.system.cache.repo.ScheduleCacheRepository;
import com.education.system.dto.course.*;
import com.education.system.exception.EntityAlreadyExistingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    ScheduleCacheRepository scheduleCacheRepository;

    public ViewCoursesResponse viewCourses(){

    }

    public CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest) {

    }

    public UploadScheduleResponse uploadSchedule(String courseCode, MultipartFile schedule){

        //Check in Cache
        Optional<ScheduleCacheEntity> scheduleCacheEntity = scheduleCacheRepository.findById(courseCode);
        if(scheduleCacheEntity.isPresent()){
            throw new EntityAlreadyExistingException();
        }
    }
}
