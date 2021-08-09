package com.dingchw.infrastructure.commons.dto;

import java.io.Serializable;

import com.dingchw.infrastructure.commons.StatusEnum;
import com.dingchw.infrastructure.commons.exception.AbstractBusinessException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class ResponseResult<T> implements Serializable{
    private Integer code;
    private String message;
    private T data;

    public ResponseResult(){
        this(null, StatusEnum.OK);
    }

    public ResponseResult(T data){
        this(data, StatusEnum.OK);
    }

    public ResponseResult(T data, StatusEnum status){
        this.data = data;
        this.code = status.getCode();
        this.message = status.getMsg();
    }

    public ResponseResult(AbstractBusinessException businessException){
        this.code = businessException.statusCode();
        this.message = businessException.getMessage();
    }

    public ResponseResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
