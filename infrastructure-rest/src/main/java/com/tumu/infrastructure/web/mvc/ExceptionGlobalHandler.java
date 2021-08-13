package com.tumu.infrastructure.web.mvc;

import com.tumu.infrastructure.commons.dto.ResponseResult;
import com.tumu.infrastructure.commons.exception.AbstractBusinessException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionGlobalHandler {
    
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult<Object> handlerException(Exception e){
        e.printStackTrace();
        if(e instanceof AbstractBusinessException){
            AbstractBusinessException abstractBusinessException = (AbstractBusinessException) e;
            return new ResponseResult<>(abstractBusinessException);
        }else{
            return new ResponseResult<>(5000, e.getMessage());
        }
    }
}
