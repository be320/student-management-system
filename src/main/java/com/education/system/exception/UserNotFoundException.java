package com.education.system.exception;

import com.education.system.util.enums.ErrorEnum;

public class UserNotFoundException extends RuntimeException{

    public String errorCode;

    public String errorMessage;

    public UserNotFoundException(){
        this.errorCode = ErrorEnum.USER_NOT_FOUND.code;
        this.errorMessage = ErrorEnum.USER_NOT_FOUND.message;
    }
}
