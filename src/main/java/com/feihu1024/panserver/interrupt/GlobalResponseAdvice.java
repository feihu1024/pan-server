package com.feihu1024.panserver.interrupt;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Controller响应拦截
 */

@ControllerAdvice()
public class GlobalResponseAdvice implements ResponseBodyAdvice {

    // 处理自定义异常
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ResponseResult customExceptionHandler(CustomException e) {
        return ResponseResult.defineError(e);
    }

    // 处理其他异常
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult exceptionHandler(Exception e) {
        return ResponseResult.defineError(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return methodParameter.getGenericParameterType().getTypeName() == "com.feihu1024.panserver.interrupt.ResponseResult";
    }

    /*
        捕获全局异常，但需要通过supports进行判断，只对ExceptionHandler增强过的响应进行拦截，否则swagger访问会出现异常
        如果ControllerAdvice注解中指定了controller包名，失败的请求无法在beforeBodyWrite拦截
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        HttpStatus httpStatus = HttpStatus.valueOf(((ResponseResult) o).getCode());
        serverHttpResponse.setStatusCode(httpStatus);
        return o;
    }
}
