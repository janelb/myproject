package com.libang.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author libang
 * @date 2018/7/16 17:55
 */

//基于内置注解
/*@Component("aopAspect")
@Aspect*/
public class AopAspect {
    @Pointcut("execution(* com.libang.service..*.*(..))")
    public void pointCut() {
    }

   /* @Before("pointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        *//*获取目标类的方法名 *//*
        System.out.println(methodName);
        System.out.println("前置通知");
    }

    @AfterReturning(value ="pointCut()" , returning="result")
    *//*JoinPoint 要放在参数类表的第一位*//*
    public void afterAdvice(JoinPoint joinPoint,Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);
        System.out.println("后置通知---->"+ result );
    }

    @AfterThrowing(value = "pointCut()" ,throwing = "ex")
    public void exceptionAdvice(JoinPoint joinPoint,Exception ex) {
        String methodName  = joinPoint.getSignature().getName();
        System.out.println(methodName);
        System.out.println("异常通知---->" +ex.getMessage());
    }
    @After("pointCut()")
    public void finallyAdvice(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName);
        System.out.println("最终通知");
    }*/

    /*环绕通知*/
    @Around("pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {

        Object result = null;
        System.out.println("前置通知");

        try {
            //进行目标方法的执行
            result = proceedingJoinPoint.proceed();
            System.out.println("后置通知");

        } catch (Throwable throwables) {

            System.out.println("异常通知");

        } finally {
            System.out.println("最终通知");
        }

        return result;

    }


}
