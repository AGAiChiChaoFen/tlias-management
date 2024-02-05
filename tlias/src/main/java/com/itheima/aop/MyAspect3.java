package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(2)
public class MyAspect3 {

    @Pointcut("execution(* com.itheima.service.Impl.DeptServiceImpl.*(..))")
    public void pt(){};

    @Before("pt()")
    public void before() {
        log.info("before...3");
    }


    @After("pt()")
    public void after() {
        log.info("after...3");
    }

}
