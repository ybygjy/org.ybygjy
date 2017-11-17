package org.ybygjy.spring.c1.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by leye on 2017/11/14.
 */
@Aspect
public class AroundHandler {
    private Logger logger = LoggerFactory.getLogger(AroundHandler.class);
    @Pointcut("execution(* org.ybygjy.spring.c1.service.t1.*.*(..)) || @annotation(LogAnnotation)")
    public void pointCut() {
    }
    @Around("pointCut()")
    public Object watchInvoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object rtnObj = null;
        try {
            Object[] args = proceedingJoinPoint.getArgs();
            Signature signature = proceedingJoinPoint.getSignature();
            String clazzName = signature.getDeclaringTypeName();
            String methodName = signature.getName();
            logger.info("before around invoke.{}.{},requestParam:{}", clazzName, methodName, Arrays.toString(args));
            long beginTime = System.currentTimeMillis();
            rtnObj = proceedingJoinPoint.proceed();
            logger.info("after around invoke.{},{},requestParam:{},responseName:{},costTime:{}", clazzName, methodName, Arrays.toString(args), rtnObj, (System.currentTimeMillis() - beginTime));
        } catch (Throwable e) {
            throw e;
        }
        return rtnObj;
    }
}
