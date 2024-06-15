package com.education.system.model;

import javax.persistence.*;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "course_code", unique = true, nullable = false)
    private String courseCode;

    @Lob
    @Column(name = "schedule")
    private byte[] schedule;

    // Constructors, getters, and setters

    public Course() {
    }

    public Course(String title, String courseCode, byte[] schedule) {
        this.title = title;
        this.courseCode = courseCode;
        this.schedule = schedule;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public byte[] getSchedule() {
        return schedule;
    }

    public void setSchedule(byte[] schedule) {
        this.schedule = schedule;
    }
}

