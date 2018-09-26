package com.chang.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;



@EnableSwagger2
@Configuration
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        Set<String> headers = new HashSet<>();
        headers.add("application/json; charset=UTF-8");
        headers.add("application/xml; charset=UTF-8");
        return new Docket(DocumentationType.SWAGGER_2).produces(headers).consumes(headers).apiInfo(apiInfo())
                .securitySchemes(Lists.newArrayList(apiKey())).select()
                .apis(RequestHandlerSelectors.basePackage("com.chang.web.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("纯注解搭建ssm框架").termsOfServiceUrl("")
                .contact(new Contact("常培兵", "", "chang_word@qq.com")).version("1.0").build();
    }

    private ApiKey apiKey() {
        return new ApiKey("AUTHORIZATION", "accessToken", "header");
    }

}
