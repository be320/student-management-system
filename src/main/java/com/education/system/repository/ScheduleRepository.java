package com.education.system.repository;

import com.education.system.entity.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<CourseSchedule, Long> {

    Optional<CourseSchedule> findByCourseId(Long courseId);
}