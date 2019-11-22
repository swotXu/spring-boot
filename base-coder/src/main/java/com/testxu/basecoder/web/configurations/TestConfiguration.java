package com.testxu.basecoder.web.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Classname: spring-boot
 * @Date: 2019/11/22 0022 15:40
 * @Author: xu.hai
 * @Description:
 */
//@EnableWebMvc
@Configuration
public class TestConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/diy/**").addResourceLocations("classpath:/bz");
        // 访问地址 http://localhost:8080/diy/test/1bztest.jpg 文件地址：D:\test\1bztest.jpg
        registry.addResourceHandler("/diy/**").addResourceLocations("file:D:/test");
        super.addResourceHandlers(registry);
    }
}
