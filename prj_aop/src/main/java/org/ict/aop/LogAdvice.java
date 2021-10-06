package org.ict.aop;

import org.springframework.stereotype.Component;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
	// 1번 advice
	@Before("execution(* org.ict.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("==================");
	}
	
	// 2번 advice
	@Before("execution(* org.ict.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	// 3번 advice
	@AfterThrowing(pointcut = "execution(* org.ict.service.SampleService*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		
		log.info("Exception....!!!!");
		log.info("exception : " + exception);
	}
	
	// 4번 advice
	@Around("execution(* org.ict.service.SampleService*.*(..))")
	public Object logTie(ProceedingJoinPoint pjp) {
		long start = System.currentTimeMillis();
		
		log.info("Target : " + pjp.getTarget());
		log.info("Param : " + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		try {
			result = pjp.proceed();
		} catch(Throwable e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		log.info("TIME : " + (end - start));
		
		return result;
	}
	
	// 5번 advice
	@After("execution(* org.ict.*.*.*(..))")  // 전역 변수로 모두 적용
	public void endLogging() {
		log.info("메서드 끝!");
	}
	
}
