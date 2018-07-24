package com.libang.controller.controllerException;

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


}
