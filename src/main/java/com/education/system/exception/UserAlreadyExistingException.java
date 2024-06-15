package com.education.system.exception;

import com.education.system.util.enums.ErrorEnum;

public class UserAlreadyExistingException extends RuntimeException{

    public String errorCode;

    public String errorMessage;

    public UserAlreadyExistingException(){
        this.errorCode = ErrorEnum.USER_EXISTING.code;
        this.errorMessage = ErrorEnum.USER_EXISTING.message;
    }
}
