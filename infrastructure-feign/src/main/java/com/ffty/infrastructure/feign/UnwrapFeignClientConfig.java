package com.ffty.infrastructure.feign;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;

@Configuration
public class UnwrapFeignClientConfig {
    @Bean
    @Primary
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverters){
        return new OptionalDecoder(new ResponseEntityDecoder(new UnwrapDecoder(messageConverters)));
    }
}
