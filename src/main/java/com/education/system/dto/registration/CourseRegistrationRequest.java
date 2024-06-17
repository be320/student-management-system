package com.education.system.dto.registration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.antlr.v4.runtime.misc.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseRegistrationRequest {

    @NotNull
    @JsonProperty("username")
    private String username;

    @NotNull
    @JsonProperty("courseCode")
    private String courseCode;

    public CourseRegistrationRequest(String username, String courseCode) {
        this.username = username;
        this.courseCode = courseCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
