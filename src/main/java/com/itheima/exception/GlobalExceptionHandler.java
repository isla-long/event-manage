package com.itheima.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.itheima.pojo.Result;
import com.mysql.cj.util.StringUtils;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handleException(Exception e) {
        e.printStackTrace();
        return Result.error(StringUtils.isEmptyOrWhitespaceOnly(e.getMessage()) ? "操作失败" : e.getMessage());
    }
}
