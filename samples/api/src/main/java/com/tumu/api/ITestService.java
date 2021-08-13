package com.tumu.api;

import com.tumu.ProviderConstants;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = ProviderConstants.APP_NAME)
public interface ITestService {
    @GetMapping(value="provider/test/{msg}")
    String test(@PathVariable("msg") String msg);
}
