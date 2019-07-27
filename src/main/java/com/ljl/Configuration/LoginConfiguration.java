package com.ljl.Configuration;


import com.ljl.Interceptor.LoginInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class LoginConfiguration implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {  //设置默认页面
        registry.addViewController("/").setViewName("forward:index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {    //添加拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/static/**","/login","/","/index.html");


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  //添加一个新的静态资源规则 原来的也还能用
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {   //解决跨域问题
        //添加映射路径
        registry.addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                //是否发送Cookie信息
                .allowCredentials(true)
                //放行哪些原始域(请求方式)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                //放行哪些原始域(头部信息)
                .allowedHeaders("*")
                //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                .exposedHeaders("Token");
    }
}
