package com.dingchw.controller;

import com.dingchw.api.ITestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private ITestService testService;

    @GetMapping(value="consumer/test/{msg}")
    public String test(@PathVariable("msg")String msg){
        return this.testService.test("consumer apply " + msg);
    }
}
