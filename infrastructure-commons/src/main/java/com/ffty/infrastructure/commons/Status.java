package com.ffty.infrastructure.commons;

/**
 * @author 丁成文
 * @date 2022/1/8
 */
public interface Status {
    /**
     * 获取状态码
     * @return
     */
    Integer getCode();

    /**
     * 获取信息
     * @return
     */
    String getMsg();
}
