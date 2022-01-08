package com.ffty.provider.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 丁成文
 * @date 2022/1/7
 */
@Configuration
public class DocConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop")
                .packagesToScan("com.ffty.provider.service")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
                .info(new Info().title("SpringShop API")
                        .description("Spring shop sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}
