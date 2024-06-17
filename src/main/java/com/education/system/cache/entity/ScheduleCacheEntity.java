package com.education.system.cache.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

// 5 days saving in the cache
@RedisHash(value = "ScheduleStore", timeToLive = 432000)
public class ScheduleCacheEntity {

    @Id
    private Long courseId;

    private String courseCode;

    private byte[] schedule;

    public ScheduleCacheEntity() {
    }

    public ScheduleCacheEntity(String courseCode, Long courseId, byte[] schedule) {
        this.courseCode = courseCode;
        this.courseId = courseId;
        this.schedule = schedule;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public byte[] getSchedule() {
        return schedule;
    }

    public void setSchedule(byte[] schedule) {
        this.schedule = schedule;
    }
}
