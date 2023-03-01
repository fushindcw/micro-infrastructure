package com.fushindcw.infrastructure.commons.exception;

import com.fushindcw.infrastructure.commons.MessageTypeEnum;
import com.fushindcw.infrastructure.commons.Status;

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
    public abstract Enum<? extends Status> status();

    public MessageTypeEnum messageType(){
        return MessageTypeEnum.WARN;
    }
}
