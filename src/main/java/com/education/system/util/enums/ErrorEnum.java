package com.education.system.util.enums;

public enum ErrorEnum {
    INVALID_PASSWORD("ERR-001", "Invalid User Password"),
    INVALID_TOKEN("ERR-002", "Invalid User Token"),
    TECHNICAL_ERROR("ERR-003", "Technical Error Contact The Support team"),
    USER_EXISTING("ERR-004", "User already exists"),
    USER_NOT_FOUND("ERR-005", "User not found");

    public final String code;
    public final String message;

    ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
