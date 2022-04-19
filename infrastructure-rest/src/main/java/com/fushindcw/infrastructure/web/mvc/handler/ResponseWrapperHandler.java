package com.fushindcw.infrastructure.web.mvc.handler;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.fushindcw.infrastructure.commons.dto.ResponseResult;

import com.fushindcw.infrastructure.web.WrapperProperties;
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

/**
 * @author dingchw
 */
public final class ResponseWrapperHandler implements HandlerMethodArgumentResolver, HandlerMethodReturnValueHandler {

    private final RequestResponseBodyMethodProcessor adapter;
    private final WrapperProperties wrapperProperties;

    public ResponseWrapperHandler(RequestResponseBodyMethodProcessor adapter, WrapperProperties wrapperProperties) {
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
        List<String> scanPackageList = this.wrapperProperties.getScanPackage();
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        if (null != request && null != response && !CollectionUtils.isEmpty(scanPackageList)) {
            String classPackage = returnType.getDeclaringClass().getPackage().getName();
            boolean match = matchPackage(scanPackageList, classPackage);
            if (match) {
                if (returnValue instanceof ResponseResult) {
                    mavContainer.setRequestHandled(true);
                    response.getWriter().write(JSON.toJSONString(returnValue));
                } else {
                    mavContainer.setRequestHandled(true);
                    ResponseResult<Serializable> wrapperResult = new ResponseResult<>(Serializable.class.cast(returnValue));
                    response.getWriter().write(JSON.toJSONString(wrapperResult));
                }
            } else {
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

    private Boolean matchPackage(List<String> packageList, String classPackage) {
        long count = packageList.stream()
                .filter(classPackage::contains)
                .count();
        return count > 0;
    }
}
