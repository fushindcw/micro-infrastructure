package com.tumu.infrastructure.commons;

import lombok.Getter;

@Getter
public enum StatusEnum {
    OK(2000, "SUCCESS"),
    NOT_FOUND(4004, "资源找不到");

    private Integer code;
    private String msg;

    StatusEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
