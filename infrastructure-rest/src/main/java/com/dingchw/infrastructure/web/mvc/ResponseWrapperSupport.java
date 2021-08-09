package com.dingchw.infrastructure.web.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Component
public class ResponseWrapperSupport extends WebMvcConfigurationSupport{
    @Override
    public RequestMappingHandlerAdapter createRequestMappingHandlerAdapter(){
        RequestMappingHandlerAdapter adapter = super.createRequestMappingHandlerAdapter();
        adapter.setReturnValueHandlers(Arrays.asList(new ResponseWrapperHandler()));
        return adapter;
    }
    
}
