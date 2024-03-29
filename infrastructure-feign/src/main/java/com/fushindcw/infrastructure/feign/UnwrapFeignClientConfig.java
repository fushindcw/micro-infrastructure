package com.fushindcw.infrastructure.feign;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;

/**
 * @author dingchw
 */
@Configuration
public class UnwrapFeignClientConfig {
    @Bean
    @Primary
    public Decoder decoder(ObjectFactory<HttpMessageConverters> messageConverters, ObjectProvider<HttpMessageConverterCustomizer> customizers){
        return new OptionalDecoder(new ResponseEntityDecoder(new UnwrapDecoder(messageConverters, customizers)));
    }
}
