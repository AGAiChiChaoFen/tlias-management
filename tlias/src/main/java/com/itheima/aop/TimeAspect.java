package com.itheima.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //声明为aop类
public class TimeAspect {

    @Around("execution(* com.itheima.service.*.*(..))")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.记录方法运行开始时间
        long begin = System.currentTimeMillis();

        //2.调用原始方法执行
        Object result = joinPoint.proceed();

        //3.记录结束时间，记录方法执行耗时
        long end = System.currentTimeMillis();

        System.out.println(joinPoint.getSignature() + "方法执行耗时为：" + (end - begin) + "ms");

        return result;
    }
}
