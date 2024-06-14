package com.education.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.antlr.v4.runtime.misc.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    @NotNull
    @JsonProperty("username")
    private String username;

    @NotNull
    @JsonProperty("token")
    private String token;

    public LoginResponse(String username, String token){
        this.username = username;
        this.token = token;
    }


    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
