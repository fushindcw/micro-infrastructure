package com.ffty.infrastructure.web.mvc.adapter;

import com.ffty.infrastructure.web.mvc.handler.ResponseWrapperHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

public class RequestMappingHandlerWrapperAdapter extends RequestMappingHandlerAdapter {

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        List<HandlerMethodReturnValueHandler> originList = super.getReturnValueHandlers();
        RequestResponseBodyMethodProcessor responseBodyMethodProcessor = originList.stream()
                .filter(item->item instanceof RequestResponseBodyMethodProcessor)
                .map(item->(RequestResponseBodyMethodProcessor)item)
                .findFirst().orElse(null);
        List<HandlerMethodReturnValueHandler> addingList = new ArrayList<>(originList);
        if(null != responseBodyMethodProcessor){
            addingList.set(addingList.indexOf(responseBodyMethodProcessor), new ResponseWrapperHandler(responseBodyMethodProcessor));
        }
        super.setReturnValueHandlers(addingList);
    }
}
