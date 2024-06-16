package com.education.system.repository;

import com.education.system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseCode(String courseCode);
}
