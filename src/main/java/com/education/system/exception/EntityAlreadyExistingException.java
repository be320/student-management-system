package com.education.system.exception;

import com.education.system.util.enums.ErrorEnum;

public class EntityAlreadyExistingException extends RuntimeException{

    public String errorCode;

    public String errorMessage;

    public EntityAlreadyExistingException(){
        this.errorCode = ErrorEnum.ENTITY_EXISTING.code;
        this.errorMessage = ErrorEnum.ENTITY_EXISTING.message;
    }
}
