package com.brokenFirmChallenge.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspect {

    @Before("execution(* com.brokenFirmChallenge.controller.*.*(..))")
    public void checkAuthorization(JoinPoint joinPoint) throws Exception {
    }
}
