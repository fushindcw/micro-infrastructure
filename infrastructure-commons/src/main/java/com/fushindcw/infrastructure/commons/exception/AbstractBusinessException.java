package com.fushindcw.infrastructure.commons.exception;

import com.fushindcw.infrastructure.commons.MessageTypeEnum;

/**
 * @author dingchw
 */
public abstract class AbstractBusinessException extends RuntimeException{
    protected AbstractBusinessException(String msg){
        super(msg);
    }

    protected AbstractBusinessException(Throwable t){
        super(t);
    }

    /**
     * 获取状态码
     * @return
     */
    public abstract Integer statusCode();

    public MessageTypeEnum messageType(){
        return MessageTypeEnum.WARN;
    }
}
