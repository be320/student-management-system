package com.education.system.cache.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "CourseStore", timeToLive = 432000)
public class CourseCacheEntity {

    @Id
    private String courseCode;

    private Long courseId;

    private String title;

    public CourseCacheEntity(String title, Long courseId, String courseCode) {
        this.courseCode = courseCode;
        this.courseId = courseId;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
