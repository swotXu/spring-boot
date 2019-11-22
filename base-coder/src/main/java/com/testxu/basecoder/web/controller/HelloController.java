package com.testxu.basecoder.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: spring-boot
 * @Date: 2019/11/22 0022 16:43
 * @Author: xu.hai
 * @Description:
 */
@RestController
public class HelloController {
    @RequestMapping("/hi")
    public String hello(){
        return "hello 2333!";
    }
}
