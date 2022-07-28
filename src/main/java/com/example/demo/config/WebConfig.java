package com.example.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.xml.transform.Source;

/**
 * @author ZDL
 * @ClassName WebConfig
 * @description: TODO
 * @datetime 2022年 07月 28日 23:39
 * @version: 1.0
 */
/*两个注解都可以,如果在配置文件中配置了映射且生效，可不配置此类。*/
//@Configuration
//@Component
public class WebConfig implements WebMvcConfigurer {

    /**
    * @Description 只适用于window系统
    * @param registry
    * @return void
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("==============配置路径已加载===================");
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:E:/CodeSongs/picandvideo/src/main/resources/static/upload/");
    }
}
