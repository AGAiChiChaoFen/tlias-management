package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class MyAspect8 {

    @Pointcut("execution(* com.itheima.service.Impl.DeptServiceImpl.*(..))")
    public void pt(){};

    @Before("pt()")
    public void before() {
        log.info("before...");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around before...");

        //1.获取目标对象的类名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名：{}",className);

        //2.获取目标方法的方法名
        String methodName = joinPoint.getSignature().getName();
        log.info("目标方法的方法名：{}",methodName);

        //3.获取目标方法运行时传入的参数
        Object[] args = joinPoint.getArgs();
        log.info("目标方法运行时传入的参数：{}", Arrays.toString(args));

        //4.目标方法执行
        Object result = joinPoint.proceed();

        //5.获取目标方法的返回值
        log.info("目标方法运行时的返回值：{}",result);

        log.info("around after...");

        return result;
    }

}
