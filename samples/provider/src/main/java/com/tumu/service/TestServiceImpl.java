package com.tumu.service;

import com.tumu.api.ITestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Optional;

@Controller
public class TestServiceImpl implements ITestService{

    @Override
    public String test(String msg) {
        System.out.println("打印>>>" + msg);
        return "hello " + msg;
    }

    @GetMapping(value="/download")
    public ResponseEntity<byte[]> downloadImage(){
        String fileName = "C:\\Users\\dingchw\\Pictures\\Camera Roll\\1.jpg";
        try(InputStream is = new FileInputStream(fileName)){
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(buffer, headers, HttpStatus.OK);
        }catch(Exception ex){

        }
        return null;
    }

    @PostMapping(value="/test02")
    @ResponseBody
    public Map<String,String> test02(@RequestBody Map<String,String> body){
        return body;
    }
}
