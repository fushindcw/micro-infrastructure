package com.tumu.infrastructure.web.mvc;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.tumu.infrastructure.commons.dto.ResponseResult;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

public final class ResponseWrapperHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Class<?> targetClass = returnType.getMethod().getDeclaringClass();
        Annotation anno = targetClass.getAnnotation(RestController.class);
        return null != anno;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        if (returnValue instanceof ResponseResult) {
            webRequest.getNativeResponse(HttpServletResponse.class).getWriter().write(JSON.toJSONString(returnValue));
        } else {
            ResponseResult<Object> wrapperResult = new ResponseResult<>(returnValue);
            webRequest.getNativeResponse(HttpServletResponse.class).getWriter().write(JSON.toJSONString(wrapperResult));
        }
    }

}
