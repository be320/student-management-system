package com.education.system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Course_Schedule")
public class CourseSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Lob
    @Column(name = "schedule", nullable = false)
    private byte[] schedule;

    // Constructors, getters, and setters
    public CourseSchedule() {}

    public CourseSchedule(Course course, byte[] schedule) {
        this.course = course;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public byte[] getSchedule() {
        return schedule;
    }

    public void setSchedule(byte[] schedule) {
        this.schedule = schedule;
    }
}
