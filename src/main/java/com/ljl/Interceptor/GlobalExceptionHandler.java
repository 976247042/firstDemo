package com.ljl.Interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
*  全局捕获异常
*  1.捕获返回的是json格式的异常
*  2.捕获的是返回的是页面的异常
*
* */
@ControllerAdvice(basePackages = "com.ljl.controller")
public class GlobalExceptionHandler {

//    @ResponseBody表示的是返回的是json格式的
//    返回ModelAndView表示的是返回的是视图页面
    @ExceptionHandler(RuntimeException.class)   //表示拦截所有的运行时异常
    @ResponseBody
    public Map<String,Object> err(HttpServletResponse response,Exception e){
       Map<String,Object>map = new HashMap();
       map.put("status",500);
       map.put("msg","服务器繁忙请稍后重试");
       response.setStatus(500);
       e.printStackTrace();
       return map;
    }
}
