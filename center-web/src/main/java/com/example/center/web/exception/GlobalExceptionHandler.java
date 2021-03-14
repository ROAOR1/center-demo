package com.example.center.web.exception;

import com.example.center.common.resp.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


/**
 * @Author: wwh
 * @Date: 2021/2/18
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * form-data格式的参数校验
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult bindExceptionHandler(BindException exception){
        log.error("参数异常信息 :", exception);
        StringBuffer message = new StringBuffer();
        exception.getAllErrors().forEach(error -> {
            message.append(error.getDefaultMessage()).append(",");
        });
        String result = message.toString();
        return CommonResult.fail(result.substring(0, result.length() - 1));
    }


    /**
     * get请求中的参数校验
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult ConstraintViolationExceptionHandler(ConstraintViolationException exception){
        log.error("参数异常信息 :", exception);
        StringBuffer message = new StringBuffer();
        exception.getConstraintViolations().forEach(e -> {
            message.append(e.getMessage()).append(",");
        });
        String result = message.toString();
        return CommonResult.fail(result.substring(0, result.length() - 1));
    }

    /**
     * json格式的参数校验
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CommonResult handle(MethodArgumentNotValidException exception) {
        log.error("参数异常信息：", exception);
        StringBuffer message=new StringBuffer();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            message.append(errorMessage).append(",");
        });
        String result = message.toString();
        return CommonResult.fail(result.substring(0, result.length() - 1));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CommonResult excepitonHandler(Exception exception) {
        log.error("服务器异常 :", exception);
        return CommonResult.fail("服务器开小差了,请稍后重试!");
    }
}
