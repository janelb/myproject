package com.libang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author libang
 * @date 2018/7/24 10:50
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "资源未找到")
public class NotFindException extends RuntimeException {
}
