package com.ffty.infrastructure.web.mvc.handler;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ffty.infrastructure.commons.dto.ResponseResult;

import com.ffty.infrastructure.web.WrapperProperties;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public final class ResponseWrapperHandler implements HandlerMethodArgumentResolver, HandlerMethodReturnValueHandler {

    private final RequestResponseBodyMethodProcessor adapter;
    private final WrapperProperties wrapperProperties;

    public ResponseWrapperHandler(RequestResponseBodyMethodProcessor adapter, WrapperProperties wrapperProperties){
        this.adapter = adapter;
        this.wrapperProperties = wrapperProperties;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return adapter.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        List<String> includesPattern = this.wrapperProperties.getScanPackage();
        if(!CollectionUtils.isEmpty(includesPattern)){
            String contextPath = webRequest.getNativeRequest(HttpServletRequest.class).getServletPath();
            List<String> originPatternList = includesPattern.stream()
                    .map(this::parsePattern)
                    .collect(Collectors.toList());
            if(matchUrl(originPatternList, contextPath)){
                if (returnValue instanceof ResponseResult) {
                    mavContainer.setRequestHandled(true);
                    webRequest.getNativeResponse(HttpServletResponse.class).getWriter().write(JSON.toJSONString(returnValue));
                } else {
                    mavContainer.setRequestHandled(true);
                    ResponseResult<Object> wrapperResult = new ResponseResult<>(returnValue);
                    webRequest.getNativeResponse(HttpServletResponse.class).getWriter().write(JSON.toJSONString(wrapperResult));
                }
            }else{
                adapter.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            }
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

    private String parsePattern(String origin){
        String result = origin.replace("/**", "/[(/)[0-9a-zA-Z]]+");
        result = result.replace("/*", "/[0-9a-zA-Z]+");
        return result;
    }

    private Boolean matchUrl(List<String> originPatternList, String originUrl){
        long count = originPatternList.stream()
                .filter(item-> Pattern.matches(item, originUrl))
                .count();
        return count > 0;
    }
}
