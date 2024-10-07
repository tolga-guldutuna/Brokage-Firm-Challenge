package com.brokenFirmChallenge.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionManagementAspect {

    @Around("execution(* com.brokenFirmChallenge.service.*.*(..))")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        try {
            System.out.println("Transaction started for method: " + joinPoint.getSignature().getName());
            result = joinPoint.proceed();
            System.out.println("Transaction committed for method: " + joinPoint.getSignature().getName());
        } catch (Exception ex) {
            System.out.println("Transaction rolled back for method: " + joinPoint.getSignature().getName());
            throw ex;
        }
        return result;
    }
}
