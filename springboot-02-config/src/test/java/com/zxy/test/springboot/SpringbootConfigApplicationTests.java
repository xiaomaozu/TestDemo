package com.zxy.test.springboot;

import com.zxy.test.springboot.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * springBoot测试
 *
 * 可以在测试期间，编码一样自动注入等功能
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootConfigApplicationTests {

    @Autowired
    private Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService(){
        boolean b = ioc.containsBean("helloService02");
        System.out.println(b);
    }

    @Test
    public void contextLoads() {

        System.out.println(person);


    }

}
