package com.libang.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author libang
 * @date 2018/7/16 11:32
 */
public class AopAspect {

    public void beforeAdvice(JoinPoint joinPoint) {

        /**/
        String methodName = joinPoint.getSignature().getName();
        Object[] objs = joinPoint.getArgs();
        Object object = joinPoint.getTarget();
        /*目标对象的方法名*/
        System.out.println(methodName);
        /*获取的是目标对象*/
        System.out.println(object);

        System.out.println("前置通知");
    }
    /*后置通知有返回值*/
    public void afterAdvice(Object result){
        System.out.println("后置通知：------>" + result);

    }

    /*获取异常信息*/
    public void exceptionAdvice(Exception ex) {
        System.out.println("异常通知---------->"+ex.getMessage());
    }

    public void finallyAdvice() {
        System.out.println("最终通知");
    }


    /*必须有Object返回值*/
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("前置通知");
        Object result=null;
        try{
                //进行目标方法的执行
                result= proceedingJoinPoint.proceed();

            System.out.println("后置通知");
        }catch(Throwable throwable){
            System.out.println("异常通知");

        }finally {
            System.out.println("最终通知");

        }

        return result;

    }


}
