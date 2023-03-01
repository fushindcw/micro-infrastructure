package com.fushindcw.infrastructure.feign;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.JSON;
import com.fushindcw.infrastructure.commons.CommonStatusEnum;
import com.fushindcw.infrastructure.commons.dto.ResponseResult;
import com.fushindcw.infrastructure.commons.exception.CommonBusinessException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import feign.FeignException;
import feign.Response;

/**
 * @author dingchw
 */
public class UnwrapDecoder extends SpringDecoder{

    public UnwrapDecoder(ObjectFactory<HttpMessageConverters> messageConverters, ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        super(messageConverters, customizers);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        String responseJson = (String)super.decode(response, String.class);
        ResponseResult<?> wrapperResult = JSON.parseObject(responseJson, ResponseResult.class);
        if(!wrapperResult.getCode().equals(CommonStatusEnum.OK.getCode())){
            throw new CommonBusinessException(wrapperResult.getMessage(), wrapperResult.getCode());
        }
        String jsonData = JSON.toJSONString(wrapperResult.getData());
        return JSON.parseObject(jsonData, type);
    }
    
}
