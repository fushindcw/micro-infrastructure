package com.ffty.infrastructure.web.mvc.support;

import com.ffty.infrastructure.web.mvc.adapter.RequestMappingHandlerWrapperAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Component
public class RestfulWebMvcConfigurationSupport extends WebMvcConfigurationSupport{

    @Override
    public RequestMappingHandlerAdapter createRequestMappingHandlerAdapter(){
        return new RequestMappingHandlerWrapperAdapter();
    }
}
