package com.libang.springTask.annotation;

import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author libang
 * @date 2018/8/16 15:26
 */
@Component
public class AsyncExceptionHandler extends SimpleAsyncUncaughtExceptionHandler  {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... params){
        System.out.println("Exception message-" +throwable.getMessage() );
        System.out.println("method name - "+ method.getName());
        for(Object param : params){
            System.out.println("param value-"+param);
        }


    }


}
