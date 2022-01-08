package com.ffty.infrastructure.commons.dto;

import java.io.Serializable;

import com.ffty.infrastructure.commons.MessageTypeEnum;
import com.ffty.infrastructure.commons.CommonStatusEnum;
import com.ffty.infrastructure.commons.exception.AbstractBusinessException;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dingchw
 */
@Setter
@Getter
public final class ResponseResult<T extends Serializable> implements Serializable{
    private Integer code;
    private String message;
    private MessageTypeEnum messageType;
    private T data;

    public ResponseResult(){
        this(null, CommonStatusEnum.OK);
    }

    public ResponseResult(T data){
        this(data, CommonStatusEnum.OK);
    }

    public ResponseResult(T data, CommonStatusEnum status){
        this.data = data;
        this.code = status.getCode();
        this.message = status.getMsg();
    }

    public ResponseResult(AbstractBusinessException businessException){
        this.code = businessException.statusCode();
        this.message = businessException.getMessage();
        this.messageType = businessException.messageType();
    }

    public ResponseResult(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, String message, MessageTypeEnum messageType){
        this.code = code;
        this.message = message;
        this.messageType = messageType;
    }
}
