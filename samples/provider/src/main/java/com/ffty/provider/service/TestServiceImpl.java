package com.ffty.provider.service;

import com.ffty.api.ITestService;

import com.ffty.infrastructure.commons.CommonStatusEnum;
import com.ffty.infrastructure.commons.exception.CommonBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * @author dingchw
 */
@Controller
@Slf4j
public class TestServiceImpl implements ITestService {

    @Override
    @ResponseBody
    public String test(String msg) {
        log.info("test>>>{}", msg);
        return "hello " + msg;
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> downloadImage() {
        String fileName = "C:\\Users\\dingchw\\Pictures\\Camera Roll\\1.jpg";
        try (InputStream is = new FileInputStream(fileName)) {
            byte[] buffer = new byte[is.available()];
            int a = is.read(buffer);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(buffer, headers, HttpStatus.OK);
        } catch (Exception ex) {
            throw new CommonBusinessException(ex);
        }
    }

    @GetMapping(value = "/test02")
    @ResponseBody
    public Map<String, String> test02(@RequestBody Map<String, String> body) {
        throw new CommonBusinessException("hello", CommonStatusEnum.NOT_FOUND.getCode());
    }
}
