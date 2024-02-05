package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1)
public class MyAspect4 {

    @Pointcut("execution(* com.itheima.service.Impl.DeptServiceImpl.*(..))")
    public void pt(){};

    @Before("pt()")
    public void before() {
        log.info("before...4");
    }

    @After("pt()")
    public void after() {
        log.info("after...4");
    }

}
