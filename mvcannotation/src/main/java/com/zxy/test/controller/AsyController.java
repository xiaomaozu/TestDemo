package com.zxy.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

@Controller
public class AsyController {

    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01(){

        System.out.println("主线程开始。。。"+Thread.currentThread()+"==>"+System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {
            //正常call执行的为业务层模块
            public String call() throws Exception {
                System.out.println("子线程开始。。。"+Thread.currentThread()+"==>"+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("主线程结束。。。"+Thread.currentThread()+"==>"+System.currentTimeMillis());
                return "async01";
            }
        };
        System.out.println("主线程结束。。。"+Thread.currentThread()+"==>"+System.currentTimeMillis());
        return callable;
    }

}
