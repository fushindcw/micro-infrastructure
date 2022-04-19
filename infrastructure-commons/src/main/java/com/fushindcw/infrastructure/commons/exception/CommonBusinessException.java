package com.fushindcw.infrastructure.commons.exception;

import com.fushindcw.infrastructure.commons.CommonStatusEnum;
import com.fushindcw.infrastructure.commons.Status;

/**
 * @author dingchw
 */
public final class CommonBusinessException extends AbstractBusinessException{
    private final Integer statusCode;

    public CommonBusinessException(Status status){
        super(status.getMsg());
        this.statusCode = status.getCode();
    }
    public CommonBusinessException(String msg, Status status) {
        super(msg);
        this.statusCode = status.getCode();
    }

    public CommonBusinessException(String msg, Integer statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

    public CommonBusinessException(Throwable t, Integer statusCode) {
        super(t);
        this.statusCode = statusCode;
    }

    public CommonBusinessException(Throwable t) {
        super(t);
        this.statusCode = CommonStatusEnum.ERROR.getCode();
    }

    @Override
    public Integer statusCode() {
        return this.statusCode;
    }
    
}
