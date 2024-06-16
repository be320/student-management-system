package com.education.system.dto.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.antlr.v4.runtime.misc.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("courseCode")
    private String courseCode;

    public CourseDTO(String title, String courseCode) {
        this.title = title;
        this.courseCode = courseCode;
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
}
