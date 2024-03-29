package com.ljl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
*  1.扫包优化 使用定位扫包的方式
*  EnableAutoConfiguration
*  ComponentScan({''})
*  2.jvm参数调优
*    内部调优  堆内存和最大内存保持一直 :-XX:+PrintGCDetails -Xmx1024m -Xms1024m
     外部调优:java -server -Xms32m -Xmx32m -jar jar包名
* */
@SpringBootApplication
//该注解默认等同于EnableAutoConfiguration + ComponentScan(默认为扫描该文件所在包以及他的所有子包)
//所以需要将他放到跟包下才可以扫描到所以的注解
@MapperScan(basePackages = {"com.ljl.dao"})  //扫描mapper接口
public class FirstSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringBootApplication.class, args);
    }

}
