package com.education.system.util.enums;

public enum ErrorEnum {
    INVALID_PASSWORD("ERR-001", "Invalid User Password"),
    INVALID_TOKEN("ERR-002", "Invalid User Token"),
    UNAUTHORIZED("ERR-003", "UNAUTHORIZED"),
    USER_EXISTING("ERR-004", "User already exists");

    public final String code;
    public final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
