package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect1 {

    @Pointcut("execution(* com.itheima.service.Impl.DeptServiceImpl.*(..))")
    public void pt(){};

    @Before("pt()")
    public void before() {
        log.info("before...");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around before...");

        //调用原始目标方法并执行
        Object result = joinPoint.proceed();

        log.info("around after...");

        return result;
    }

    @After("pt()")
    public void after() {
        log.info("after...");
    }

    @AfterReturning("pt()")
    public void afterReturning() {
        log.info("afterReturning...");
    }

    @AfterThrowing("pt()")
    public void afterThrowing() {
        log.info("afterThrowing...");
    }
}
