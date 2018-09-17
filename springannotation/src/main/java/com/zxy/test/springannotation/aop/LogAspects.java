package com.zxy.test.springannotation.aop;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 切面类
 * @author lfy
 *
 * @Aspect： 告诉Spring当前类是一个切面类
 *
 */
@Aspect
public class LogAspects {

	//抽取公共的切入点表达式
	//1、本类引用
	//2、其他的切面引用
	@Pointcut("execution(public int com.zxy.test.springannotation.aop.MathCalculator.*(..))")
	public void pointCut(){};

	/**
	 * @Before在目标方法之前切入；切入点表达式（指定在哪个方法切入）
	 * //本类引用，
	 */
	@Before(value = "pointCut()")
	public void logStart(JoinPoint joinPoint){
		//	getSignature()获取签名
		//  getArgs()  参数表
		Object[] args = joinPoint.getArgs();
		System.out.println(""+joinPoint.getSignature().getName()+"除法开始。。。参数列表是"+Arrays.asList(args));
	}

	//@After("public int com.zxy.test.springannotation.aop.MathCalculator.*(..)") //如果是其他的切面引用，那么久引用全名
	@After(value = "pointCut()")
	public void logEnd(JoinPoint joinPoint){
		System.out.println(""+joinPoint.getSignature().getName()+"除法结束。。。");
	}

	@AfterReturning(value = "pointCut()",returning = "result")
	public void logReturn(JoinPoint joinPoint,Object result){
		System.out.println("除非正常返回。。。。运行结果:{"+result+"}");
	}

	//******JoinPoint一定要出现在参数表的第一位
	@AfterThrowing(value = "pointCut()",throwing = "exception")
	public void logException(JoinPoint joinPoint,Exception exception){
		System.out.println(joinPoint.getSignature().getName()+"除非异常信息。。。异常信息:{"+exception.getMessage()+"}");
		//System.out.println("除非异常信息。。。异常信息");
	}
}
