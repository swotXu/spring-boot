package com.testwork.demo.StartDemo;

import bbt.BbtGirl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    BbtGirl bbtGirl;

    @RequestMapping("/testStart")
    public String test(){
        String result = "bbtGirl==null？" + (bbtGirl == null);
        System.out.println(result);
        System.out.println(bbtGirl);
        System.out.println(bbtGirl.getAge());
        System.out.println(bbtGirl.getLength());
        System.out.println(bbtGirl.getName());
        return result;
    }
}
