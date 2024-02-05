package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class MyAspect7 {

    @Pointcut("@annotation(com.itheima.aop.MyLog)")
    public void pt(){};

    @Before("pt()")
    public void before() {
        log.info("before...2");
    }

    @After("pt()")
    public void after() {
        log.info("after...2");
    }

}
