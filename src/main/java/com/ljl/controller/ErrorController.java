package com.ljl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*  全局捕获异常
*
* */
@RestController
public class ErrorController {

    public String a = null;

    @RequestMapping("get")
    public String get(){
        int a = 1/0;
        return "你好";
    }

    @RequestMapping("null")
    public String fn1(){
        return a.toString();
    }

}
