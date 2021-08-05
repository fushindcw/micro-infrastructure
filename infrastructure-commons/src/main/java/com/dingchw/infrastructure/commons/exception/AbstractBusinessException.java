package com.dingchw.infrastructure.commons.exception;

public abstract class AbstractBusinessException extends RuntimeException{
    protected AbstractBusinessException(String msg){
        super(msg);
    }

    protected AbstractBusinessException(Throwable t){
        super(t);
    }
    
    public abstract Integer statusCode();
}
