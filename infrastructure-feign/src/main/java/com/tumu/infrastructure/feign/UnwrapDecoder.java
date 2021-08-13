package com.tumu.infrastructure.feign;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.JSON;
import com.tumu.infrastructure.commons.StatusEnum;
import com.tumu.infrastructure.commons.dto.ResponseResult;
import com.tumu.infrastructure.commons.exception.CommonBusinessException;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import feign.FeignException;
import feign.Response;

public class UnwrapDecoder extends SpringDecoder{

    public UnwrapDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        String responsejson = (String)super.decode(response, String.class);
        ResponseResult<?> wrapperResult = JSON.parseObject(responsejson, ResponseResult.class);
        if(!wrapperResult.getCode().equals(StatusEnum.OK.getCode())){
            throw new CommonBusinessException(wrapperResult.getMessage(), wrapperResult.getCode());
        }
        String jsonData = JSON.toJSONString(wrapperResult.getData());
        return JSON.parseObject(jsonData, type);
    }
    
}
