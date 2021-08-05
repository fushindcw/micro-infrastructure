package com.dingchw.infrastructure.commons.exception;

public final class CommonBusinessException extends AbstractBusinessException{
    private Integer statusCode;

    public CommonBusinessException(String msg, Integer statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

    public CommonBusinessException(Throwable t, Integer statusCode) {
        super(t);
        this.statusCode = statusCode;
    }

    @Override
    public Integer statusCode() {
        return this.statusCode;
    }
    
}
