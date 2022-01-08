package com.ffty.api;

import com.ffty.ProviderConstants;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author dingchw
 */
@FeignClient(value = ProviderConstants.APP_NAME)
public interface ITestService {
    /**
     * test
     * @param msg
     * @return
     */
    @GetMapping(value="provider/test/{msg}")
    String test(@PathVariable("msg") String msg);
}
