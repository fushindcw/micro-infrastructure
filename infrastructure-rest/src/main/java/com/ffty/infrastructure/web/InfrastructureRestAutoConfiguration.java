package com.ffty.infrastructure.web;

import com.ffty.infrastructure.commons.MessageTypeEnum;
import com.ffty.infrastructure.commons.dto.ResponseResult;
import com.ffty.infrastructure.commons.exception.AbstractBusinessException;
import com.ffty.infrastructure.web.mvc.adapter.RequestMappingHandlerWrapperAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.io.Serializable;

/**
 * @author dingchw
 */
@Configuration
public class InfrastructureRestAutoConfiguration{

    @Bean
    @ConfigurationProperties(prefix="spring.response")
    public WrapperProperties wrapperProperties(){
        return new WrapperProperties();
    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public class RequestMappingHandlerRegistrations implements WebMvcRegistrations {
        @Autowired
        private WrapperProperties wrapperProperties;
        @Override
        public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
            return new RequestMappingHandlerWrapperAdapter(this.wrapperProperties);
        }
    }

    @Configuration(proxyBeanMethods = false)
    @ControllerAdvice
    public static class ExceptionGlobalHandler {
        @Value(value="${exception.display.enable:false}")
        private boolean displayException;

        @ResponseBody
        @ExceptionHandler(Exception.class)
        public <T extends Serializable> ResponseResult<T> handlerException(Exception e){
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
