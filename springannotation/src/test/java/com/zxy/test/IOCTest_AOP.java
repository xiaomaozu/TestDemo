package com.zxy.test;

import com.zxy.test.springannotation.aop.MathCalculator;
import com.zxy.test.springannotation.bean.Boss;
import com.zxy.test.springannotation.bean.Car;
import com.zxy.test.springannotation.bean.Color;
import com.zxy.test.springannotation.config.MainConfigOfAOP;
import com.zxy.test.springannotation.config.MainConifgOfAutowired;
import com.zxy.test.springannotation.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_AOP {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

		//1.不要自己创建
		//MathCalculator mathCalculator=new MathCalculator();

		MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
		int result = mathCalculator.div(6, 1);
	//	System.out.println(result);
		applicationContext.close();
	}

}
