package com.tumu.infrastructure.web.mvc;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.function.support.HandlerFunctionAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@Component
public class RestfulWebMvcConfigurationSupport extends WebMvcConfigurationSupport{

    @Override
    public RequestMappingHandlerAdapter createRequestMappingHandlerAdapter(){
        return new RequestMappingHandlerWrapperAdapter();
    }



}
