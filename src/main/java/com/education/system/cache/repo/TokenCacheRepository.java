package com.education.system.cache.repo;

import com.education.system.cache.entity.TokenCacheEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenCacheRepository extends CrudRepository<TokenCacheEntity, String> {
}
