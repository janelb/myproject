package com.libang.erp.controller.controllerException;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * @author libang
 * @date 2018/7/24 10:37
 */
@ControllerAdvice
public class ControllerExceptionHandler{

    @ExceptionHandler(IOException.class)
    public String ioExcweption(){

        return "error/500";
    }

    /*如果不配置没有权限访问时会报异常*/

    @ExceptionHandler(AuthorizationException.class)
    public String authorizationException() {
        return "error/401";
    }


}
