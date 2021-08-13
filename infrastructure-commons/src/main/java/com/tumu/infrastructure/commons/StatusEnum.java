package com.tumu.infrastructure.commons;

import lombok.Getter;

@Getter
public enum StatusEnum {
    NOT_FOUND(4004, "资源找不到"),
    OK(1000, "SUCCESS");

    private Integer code;
    private String msg;

    StatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
