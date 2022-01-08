package com.ffty.infrastructure.commons;

import lombok.Getter;

/**
 * @author dingchw
 */

@Getter
public enum CommonStatusEnum implements Status{
    /**
     * 成功
     */
    OK(2000, "SUCCESS"),
    /**
     * 资源找不到
     */
    NOT_FOUND(4004, "资源找不到"),
    /**
     * 服务器内部错误
     */
    ERROR(5000,"服务器内部错误");

    private Integer code;
    private String msg;

    CommonStatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
