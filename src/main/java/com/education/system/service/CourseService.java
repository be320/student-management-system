package com.education.system.service;

import com.education.system.cache.entity.CourseCacheEntity;
import com.education.system.cache.entity.ScheduleCacheEntity;
import com.education.system.cache.repo.CourseCacheRepository;
import com.education.system.cache.repo.ScheduleCacheRepository;
import com.education.system.dto.course.*;
import com.education.system.entity.CourseSchedule;
import com.education.system.exception.EntityAlreadyExistingException;
import com.education.system.entity.Course;
import com.education.system.exception.EntityNotFoundException;
import com.education.system.repository.CourseRepository;
import com.education.system.repository.ScheduleRepository;
import com.education.system.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    @Autowired
    ScheduleCacheRepository scheduleCacheRepository;

    @Autowired
    CourseCacheRepository courseCacheRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public ViewCoursesResponse viewCourses(){

        List<CourseDTO> coursesDTO = new ArrayList<>();

        //Check in cache
        List<CourseCacheEntity> courseCacheEntities = CommonUtil.convertIterableToList(courseCacheRepository.findAll());
        if (!courseCacheEntities.isEmpty()){
            courseCacheEntities.forEach(entity -> coursesDTO.add(new CourseDTO(entity.getTitle(), entity.getCourseCode())));
            return new ViewCoursesResponse(coursesDTO);
        }

        //Check in database
        else{
            List<Course> courses = courseRepository.findAll();
            courses.forEach(course -> coursesDTO.add(new CourseDTO(course.getTitle(), course.getCourseCode())));
            return new ViewCoursesResponse(coursesDTO);
        }
    }

    public CreateCourseResponse createCourse(CreateCourseRequest createCourseRequest) {

        //Check in cache
        Optional<CourseCacheEntity> courseCacheEntity = courseCacheRepository.findByCourseCode(createCourseRequest.getCourseCode());
        if(courseCacheEntity.isPresent()){
            throw new EntityAlreadyExistingException();
        }

        //Check in database
        else if(courseRepository.findByCourseCode(createCourseRequest.getCourseCode()).isPresent()){
            throw new EntityAlreadyExistingException();
        }

        //Save in database and cache
        else {
            Course course = courseRepository.save(new Course(createCourseRequest.getTitle(), createCourseRequest.getCourseCode()));
            courseCacheRepository.save(new CourseCacheEntity(course.getTitle(), course.getId(),course.getCourseCode()));
        }
        return new CreateCourseResponse(createCourseRequest.getTitle(), createCourseRequest.getCourseCode());
    }

    public String uploadSchedule(String courseCode, MultipartFile schedule) throws IOException {
        Long courseId = getCourse(courseCode);
        //Check if schedule already existing
        Optional<ScheduleCacheEntity> scheduleCacheEntity = scheduleCacheRepository.findById(courseId);
        if(scheduleCacheEntity.isPresent()){
            throw new EntityAlreadyExistingException();
        }
        else {
            Optional<CourseSchedule> courseSchedule =  scheduleRepository.findByCourseId(courseId);
            if(courseSchedule.isPresent())
                throw new EntityAlreadyExistingException();
        }

        //Save the schedule
        Optional<Course> course =  courseRepository.findById(courseId);
        if(course.isPresent()){
            CourseSchedule courseSchedule = scheduleRepository.save(new CourseSchedule(course.get(), schedule.getBytes()));
            scheduleCacheRepository.save(new ScheduleCacheEntity(course.get().getCourseCode(), course.get().getId(), schedule.getBytes()));
        }

        return "Schedule uploaded successfully";

    }

    public byte[] downloadSchedule(String courseCode){
        Long courseId = getCourse(courseCode);
        Optional<ScheduleCacheEntity> scheduleCacheEntity = scheduleCacheRepository.findById(courseId);
        if(scheduleCacheEntity.isPresent()){
            return scheduleCacheEntity.get().getSchedule();
        }
        else {
            Optional<CourseSchedule> courseSchedule =  scheduleRepository.findByCourseId(courseId);
            if(courseSchedule.isPresent())
                return courseSchedule.get().getSchedule();
            throw new EntityNotFoundException();
        }
    }

    private Long getCourse(String courseCode){
        //Get Course Data
        Long courseId;
        Optional<CourseCacheEntity> courseCacheEntity = courseCacheRepository.findByCourseCode(courseCode);
        if(courseCacheEntity.isPresent()){
            courseId = courseCacheEntity.get().getCourseId();
        }
        else {
            Optional<Course> course =  courseRepository.findByCourseCode(courseCode);
            if(course.isEmpty())
                throw new EntityNotFoundException();
            courseId = course.get().getId();
            courseCacheRepository.save(new CourseCacheEntity(course.get().getTitle(), course.get().getId(), course.get().getCourseCode()));
        }
        return courseId;
    }
}
