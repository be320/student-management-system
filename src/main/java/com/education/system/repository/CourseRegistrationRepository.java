package com.education.system.repository;

import com.education.system.entity.CourseRegistration;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    Optional<CourseRegistration> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
