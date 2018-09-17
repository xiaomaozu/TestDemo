package com.zxy.test.springannotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

public class Red implements ApplicationContextAware,BeanNameAware,EmbeddedValueResolverAware{

    private ApplicationContext applicationContext;
    public void setBeanName(String s) {
        System.out.println(s);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的ioc"+applicationContext);
        this.applicationContext=applicationContext;
    }
    //解析spring的值
    // ${}   #{}是spring的sq
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        String resolveStringValue = stringValueResolver.resolveStringValue("你好${os.name},我是#{20*10}");
        System.out.println("解析的字符串"+resolveStringValue);
    }
}
