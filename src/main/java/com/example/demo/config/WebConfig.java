package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZDL
 * @ClassName WebConfig
 * @description: TODO
 * @datetime 2022年 07月 28日 23:39
 * @version: 1.0
 */
/*两个注解都可以,如果在配置文件中配置了映射且生效，可不配置此类。*/
@Configuration
//@Component
public class WebConfig implements WebMvcConfigurer {

    @Value("${win.base-path.photo}")
    private String photoPath;

    @Value("${win.base-path.video}")
    private String videoPath;

    /**
    * @Description 只适用于window系统
    * @param registry
    * @return void
    */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("==============配置路径已加载===================");
        String pPath = System.getProperty("user.dir") + photoPath;
        System.out.println(pPath);
        String vPath = System.getProperty("user.dir") + videoPath;
        System.out.println(vPath);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + pPath)
                .addResourceLocations("file:" + vPath);
    }
}
