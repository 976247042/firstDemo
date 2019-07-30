package com.ljl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*
*  springboot访问静态资源  将静态资源放到resource目录中的static文件夹下
*  访问静态资源的时候不用加上static
*
*  访问页面时如果是在templates目录下的页面默认是不可通过浏览器直接访问的
*  想要访问只能通过controller访问
*
* */
@RestController
public class StaticResourceController {


//    @RequestMapping("/home")
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home");
//        modelAndView.addObject("name","刘佳林");
//
//        return modelAndView;
//    }
    @RequestMapping("/index")
    public String index(){
//        System.out.println("index");
        return "";
    }
}
