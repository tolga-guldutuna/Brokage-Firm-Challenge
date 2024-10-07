package com.brokenFirmChallenge.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.brokenFirmChallenge.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        System.out.println("Method " + joinPoint.getSignature().getName() + " is called.");
    }

    @After("execution(* com.brokenFirmChallenge.service.*.*(..))")
    public void logAfterMethod(JoinPoint joinPoint) {
        System.out.println("Method " + joinPoint.getSignature().getName() + " has completed.");
    }
}
