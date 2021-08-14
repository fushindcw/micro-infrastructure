package com.tumu.infrastructure.commons.exception;

import com.tumu.infrastructure.commons.MessageTypeEnum;

public abstract class AbstractBusinessException extends RuntimeException{
    protected AbstractBusinessException(String msg){
        super(msg);
    }

    protected AbstractBusinessException(Throwable t){
        super(t);
    }
    
    public abstract Integer statusCode();

    public MessageTypeEnum messageType(){
        return MessageTypeEnum.WARN;
    }
}
