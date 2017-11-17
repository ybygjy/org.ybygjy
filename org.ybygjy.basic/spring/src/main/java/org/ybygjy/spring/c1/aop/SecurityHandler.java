package org.ybygjy.spring.c1.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by leye on 2017/11/14.
 */
@Aspect
public class SecurityHandler {
    @Pointcut("execution(* org.ybygjy.spring.c1.service.impl.*.*(..))")
    public void pointCutHandler() {
    }
    @Before("pointCutHandler()")
    public void beforeInvoke() {
        System.out.println("before Invoke.");
    }
    @AfterReturning("pointCutHandler()")
    public void afterInvoke() {
        System.out.println("after Invoke.");
    }
}
