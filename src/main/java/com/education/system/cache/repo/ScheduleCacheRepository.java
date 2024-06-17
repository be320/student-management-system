package com.education.system.cache.repo;

import com.education.system.cache.entity.ScheduleCacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleCacheRepository extends CrudRepository<ScheduleCacheEntity, Long> {

}
