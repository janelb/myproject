package com.libang.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author libang
 * @date 2018/7/16 10:59
 */
public class SubjectMethodInterceptor implements MethodInterceptor {
    /**
     * @param target 目标对象
     * @param method
     * @param args 方法参数列表
     * @param methodProxy 产生父类方法的代理对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("------------------------------------");
        Object result = methodProxy.invokeSuper(target,args);
        System.out.println("=================================");
        return result;
    }
}
