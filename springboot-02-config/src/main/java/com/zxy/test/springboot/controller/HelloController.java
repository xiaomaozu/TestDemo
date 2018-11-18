package com.zxy.test.springboot.controller;

/**
 * @author xinyao.zeng@ucarinc.com
 * @version 2018/10/29
 * @description
 * @since 2018/10/29
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${person.last-name}")
    private String name;

    @RequestMapping("/sayHello")
    public String sayHello(){
        return "Hello "+name;
    }

}

