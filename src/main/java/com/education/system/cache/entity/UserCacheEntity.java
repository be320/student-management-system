package com.education.system.cache.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

// 5 days saving the password of the user in the cache
@RedisHash(value = "UserStore", timeToLive = 432000)
public class UserCacheEntity {

    @Id
    private String username;

    private Long userId;

    private String hashedPassword;

    public UserCacheEntity(String username, Long userId, String hashedPassword){
        this.username = username;
        this.userId = userId;
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
