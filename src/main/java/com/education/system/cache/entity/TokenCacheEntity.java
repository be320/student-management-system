package com.education.system.cache.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "TokenStore", timeToLive = 600)
public class TokenCacheEntity {

    @Id
    private String username;

    private String tokenHash;

    public TokenCacheEntity(String username, String tokenHash){
        this.username = username;
        this.tokenHash = tokenHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }
}
