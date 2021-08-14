package com.tumu.infrastructure.web.mvc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.tumu.infrastructure.commons.dto.ResponseResult;

import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public final class ResponseWrapperHandler implements HandlerMethodArgumentResolver, HandlerMethodReturnValueHandler {

    private final RequestResponseBodyMethodProcessor adapter;

    public ResponseWrapperHandler(RequestResponseBodyMethodProcessor adapter){
        this.adapter = adapter;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return adapter.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {

            if (returnValue instanceof ResponseResult) {
                mavContainer.setRequestHandled(true);
                webRequest.getNativeResponse(HttpServletResponse.class).getWriter().write(JSON.toJSONString(returnValue));
            } else {
                mavContainer.setRequestHandled(true);
                ResponseResult<Object> wrapperResult = new ResponseResult<>(returnValue);
                webRequest.getNativeResponse(HttpServletResponse.class).getWriter().write(JSON.toJSONString(wrapperResult));
            }
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return adapter.supportsParameter(parameter);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return adapter.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }
}
