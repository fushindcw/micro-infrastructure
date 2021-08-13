package com.tumu.service;

import com.tumu.api.ITestService;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestServiceImpl implements ITestService{

    @Override
    public String test(String msg) {
        System.out.println("打印>>>" + msg);
        return "hello " + msg;
    }
    
}
