package com.education.system.cache.repo;

import com.education.system.cache.entity.CourseCacheEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CourseCacheRepository extends CrudRepository<CourseCacheEntity, Long> {
    Optional<CourseCacheEntity> findByCourseCode(String courseCode);
}
