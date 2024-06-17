package com.education.system.service;

import com.education.system.cache.entity.CourseCacheEntity;
import com.education.system.entity.Course;
import com.education.system.entity.CourseRegistration;
import com.education.system.entity.Student;
import com.education.system.exception.EntityAlreadyExistingException;
import com.education.system.exception.EntityNotFoundException;
import com.education.system.repository.CourseRegistrationRepository;
import com.education.system.repository.CourseRepository;
import com.education.system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RegistrationService {

    @Autowired
    TokenService tokenService;

    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    public String registerCourse(String username, String courseCode) {

        tokenService.checkUserAuthorization(username);
        Optional<Course> course = courseRepository.findByCourseCode(courseCode);
        Optional<Student> student = studentRepository.findByUsername(username);
        if(student.isPresent() && course.isPresent()){
            Optional<CourseRegistration> courseRegistration = courseRegistrationRepository.findByStudentIdAndCourseId(student.get().getId(), course.get().getId());
            if(courseRegistration.isPresent())
                throw new EntityAlreadyExistingException();
            courseRegistrationRepository.save(new CourseRegistration(student.get(), course.get(), false));
            return "Registered Course Successfully";
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
