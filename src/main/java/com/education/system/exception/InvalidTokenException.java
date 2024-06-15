package com.education.system.exception;

import com.education.system.util.enums.ErrorEnum;

public class InvalidTokenException extends RuntimeException{

    public String errorCode;

    public String errorMessage;

    public InvalidTokenException(){
        errorCode = ErrorEnum.INVALID_TOKEN.code;
        errorMessage = ErrorEnum.INVALID_TOKEN.message;
    }
}
