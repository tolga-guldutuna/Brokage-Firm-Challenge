package com.brokenFirmChallenge.aspect;

import com.brokenFirmChallenge.model.dto.CreateOrderRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* com.brokenFirmChallenge.service.OrderService.createOrder(..)) && args(createOrderRequest)")
    public void validateOrder(JoinPoint joinPoint, CreateOrderRequest createOrderRequest) throws Exception {
    }
}
