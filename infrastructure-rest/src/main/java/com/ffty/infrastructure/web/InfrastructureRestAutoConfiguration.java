package com.ffty.infrastructure.web;

import com.ffty.infrastructure.commons.MessageTypeEnum;
import com.ffty.infrastructure.commons.dto.ResponseResult;
import com.ffty.infrastructure.commons.exception.AbstractBusinessException;
import com.ffty.infrastructure.web.mvc.adapter.RequestMappingHandlerWrapperAdapter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
public class InfrastructureRestAutoConfiguration{

    @Configuration(proxyBeanMethods = false)
    public static class EnableRestfulWebMvcConfiguration extends WebMvcAutoConfiguration.EnableWebMvcConfiguration {
        public EnableRestfulWebMvcConfiguration(ResourceProperties resourceProperties, ObjectProvider<WebMvcProperties> mvcPropertiesProvider, ObjectProvider<WebMvcRegistrations> mvcRegistrationsProvider, ListableBeanFactory beanFactory) {
            super(resourceProperties, mvcPropertiesProvider, mvcRegistrationsProvider, beanFactory);
        }

        @Override
        public RequestMappingHandlerAdapter createRequestMappingHandlerAdapter(){
            return new RequestMappingHandlerWrapperAdapter();
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ControllerAdvice
    public static class ExceptionGlobalHandler {
        @Value(value="${exception.display.enable:false}")
        private Boolean displayException;

        @ResponseBody
        @ExceptionHandler(Exception.class)
        public ResponseResult<Object> handlerException(Exception e){
            if(this.displayException){
                e.printStackTrace();
            }
            if(e instanceof AbstractBusinessException){
                AbstractBusinessException abstractBusinessException = (AbstractBusinessException) e;
                return new ResponseResult<>(abstractBusinessException);
            }else{
                return new ResponseResult<>(5000, e.getMessage(), MessageTypeEnum.FAIL);
            }
        }
    }
}
