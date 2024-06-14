package com.education.system.cache.repo;

import com.education.system.cache.entity.TokenCacheEntity;
import com.education.system.cache.entity.UserCacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCacheRepository extends CrudRepository<UserCacheEntity, String> {
}
