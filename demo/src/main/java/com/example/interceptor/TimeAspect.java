package com.example.interceptor;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

/*@Aspect
@Component*/
public class TimeAspect {
	@Around(value = "execution(* com.example.controller..*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("time aspect start");
		long start = new Date().getTime();
		for (Object arg : pjp.getArgs()) {
			System.out.println(arg.toString());
		}
		Object o = pjp.proceed();
		long end = new Date().getTime() - start;
		System.out.println(end);
		System.out.println("time aspect finish");
		return o;

	}
}
