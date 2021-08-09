package com.dingchw.service;

import com.dingchw.api.ITestService;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
public class TestServiceImpl implements ITestService{

    @Override
    public String test(String msg) {
        System.out.println("打印>>>" + msg);
        return "hello " + msg;
    }
    
}
