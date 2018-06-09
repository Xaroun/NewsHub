package com.kociszewski.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by mateusz on 09.06.2018.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(myApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(regex("/news.*"))
                .build()
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(
                                new ResponseMessageBuilder()
                                        .code(401).message("Unauthorized due to invalid API key").build(),
                                new ResponseMessageBuilder()
                                        .code(404).message("News not found").build()));
    }

    private ApiInfo myApiInfo() {
        return new ApiInfoBuilder()
                .title("NewsHub API")
                .description("This API provides news served by external API (newsapi.org)")
                .contact(new Contact("Mateusz Kociszewski", "https://github.com/Xaroun", "mkociszewski@icloud.com"))
                .build();
    }
}
