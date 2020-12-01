package com.huangxi.aspect;

import com.huangxi.introduction.LittleUniverse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {
	@Pointcut("execution(* com.huangxi.service..*.*(..))")
	public void embed(){}

	@Before("embed()")
	public void before(JoinPoint joinPoint){
		System.out.println("start call: "+joinPoint);

	}

	@After("embed()")
	public void after(JoinPoint joinPoint){
		System.out.println("call finished: "+joinPoint);
	}

	@Around("embed()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object returnValue = null;
		System.out.println("time begins: "+joinPoint);
		// 如果这里catch了异常，@AfterThrowing 将不会被执行
		returnValue = ((ProceedingJoinPoint)joinPoint).proceed();
		System.out.println("execute finished, time stop: "+joinPoint);

		long endTime = System.currentTimeMillis();
		System.out.println("cost time: "+(endTime-startTime)+" ms");
		return returnValue;
	}

	@AfterReturning(pointcut = "embed()", returning = "returnValue")
	public void afterReturning(JoinPoint joinPoint, Object returnValue){
		System.out.println("anyway, method will call this method, joinPoint: "+joinPoint
				+", returnValue: "+returnValue);
	}

	@AfterThrowing(pointcut = "embed()", throwing = "exception")
	public void afterThrowing(JoinPoint joinPoint, Exception exception){
		System.out.println("throw exception, joinPoint: "+joinPoint+", exception: "+exception.getMessage());
	}

	// 测试 Introduction
	// @DeclareParents 是作用在成员变量(ElementType.FIELD)上的
	// 所有的package: com.huangxi.controller 下的类都继承了指定接口（LittleUniverse）， 并且由defaultImpl指定实现方法
	@DeclareParents(value = "com.huangxi.controller..*", defaultImpl = com.huangxi.introduction.impl.LittleUniverseImpl.class)
	public LittleUniverse littleUniverse;
}
