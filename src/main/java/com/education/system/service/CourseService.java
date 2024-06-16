package com.education.system.service;

import com.education.system.cache.entity.CourseCacheEntity;
import com.education.system.cache.repo.CourseCacheRepository;
import com.education.system.cache.repo.ScheduleCacheRepository;
import com.education.system.dto.course.*;
import com.education.system.exception.EntityAlreadyExistingException;
import com.education.system.entity.Course;
import com.education.system.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    ScheduleCacheRepository scheduleCacheRepository;

    @Autowired
    CourseCacheRepository courseCacheRepository;

    @Autowired
    CourseRepository courseRepository;

//    public ViewCoursesResponse viewCourses(){
//
//        //Check in cache
//        List<CourseCacheEntity> courseCacheEntities = CommonUtil.convertIterableToList(courseCacheRepository.findAll());
//        if (!courseCacheEntities.isEmpty()){
//            return new ViewCoursesResponse(courseCacheEntities.stream()
//                    .map(CourseMapper.INSTANCE::courseCacheEntityToCourseDTO)
//                    .collect(Collectors.toList()));
//        }
//
//        //Check in database
//        else{
//          List<Course>  courseRepository.findAll();
//        }
//    }

    public CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest) {

        //Check in cache
        Optional<CourseCacheEntity> courseCacheEntity = courseCacheRepository.findById(createCourseRequest.getCourseCode());
        if(courseCacheEntity.isPresent()){
            throw new EntityAlreadyExistingException();
        }

        //Check in database
        else if(courseRepository.findByCourseCode(createCourseRequest.getCourseCode()) != null){
            throw new EntityAlreadyExistingException();
        }

        //Save in database and cache
        else {
            Course course = courseRepository.save(new Course(createCourseRequest.getTitle(), createCourseRequest.getCourseCode()));
            courseCacheRepository.save(new CourseCacheEntity(course.getTitle(), course.getId(),course.getCourseCode()));
        }
        return new CreateCourseResponse(createCourseRequest.getTitle(), createCourseRequest.getCourseCode());
    }

//    public UploadScheduleResponse uploadSchedule(String courseCode, MultipartFile schedule){
//
//        //Check in Cache
//        Optional<ScheduleCacheEntity> scheduleCacheEntity = scheduleCacheRepository.findById(courseCode);
//        if(scheduleCacheEntity.isPresent()){
//            throw new EntityAlreadyExistingException();
//        }
//
//        //Check in Database
//        else if(studentRepository.findByUsername(signupRequest.getUsername()) != null){
//            throw new EntityAlreadyExistingException();
//        }
//    }
}
