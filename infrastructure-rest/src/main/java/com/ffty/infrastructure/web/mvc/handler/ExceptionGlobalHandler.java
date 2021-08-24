package com.ffty.infrastructure.web.mvc.handler;

import com.ffty.infrastructure.commons.MessageTypeEnum;
import com.ffty.infrastructure.commons.dto.ResponseResult;
import com.ffty.infrastructure.commons.exception.AbstractBusinessException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionGlobalHandler {
    @Value(value="${exception.display.enable:false}")
    private Boolean displayException;
    
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult<Object> handlerException(Exception e){
        if(this.displayException){
            e.printStackTrace();
        }
        if(e instanceof AbstractBusinessException){
            AbstractBusinessException abstractBusinessException = (AbstractBusinessException) e;
            return new ResponseResult<>(abstractBusinessException);
        }else{
            return new ResponseResult<>(5000, e.getMessage(), MessageTypeEnum.FAIL);
        }
    }
}
