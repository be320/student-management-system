package com.education.system.dto.course;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViewCoursesResponse {

    @NotNull
    @JsonProperty("courses")
    private List<CourseDTO> courses;

    public ViewCoursesResponse(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
